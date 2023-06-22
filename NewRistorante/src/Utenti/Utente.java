package Utenti;

import Ristorante.Ristorante;
import Util.*;

public abstract class Utente implements MenuUtente {

	private String nome;
	private String etichetta;

	//ci serve per il metodo del menu
	private String[] azioni;
	private MyMenu menu;

	private static final String[] MENU_RUOLI = {"Gestore", "Addetto alle prenotazioni", "Magazziniere"};
	
	public Utente(String nome, String etichetta, String[] azioni) {
		this.nome = nome;
		this.etichetta = etichetta;
		this.azioni = azioni;
		this.menu = new MyMenu("menu "+ etichetta, azioni);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEtichetta() {
		return etichetta;
	}

	public void setEtichetta(String etichetta) {
		this.etichetta = etichetta;
	}

	public String[] getAzioni() {
		return azioni;
	}

	public void setAzioni(String[] azioni) {
		this.azioni = azioni;
	}

	public void menu(Ristorante ristorante) {
		System.out.printf("Ciao %s!\n", this.nome);
		int scelta = menu.scegli();
		eseguiMetodi(ristorante, scelta);
	}

	public static void mostraMenuRuoli(String pathCompletoFile) {
		String nome = InputDati.leggiStringaNonVuota("Inserisci il tuo nome: ");
		Utente utente = null;
	    Ristorante ristorante = Ristorante.getInstance(nome, pathCompletoFile);
		MyMenu menuRuoli = new MyMenu("Seleziona il tuo ruolo:", MENU_RUOLI);
		int scelta = menuRuoli.scegli();
		switch (scelta) {
		case 1:
			utente = new Gestore(nome);
			break;
		case 2:
			utente = new AddettoPrenotazioni(nome);
			break;
		case 3:
			utente = new Magazziniere(nome);
			break;
		default:
			System.out.println("Grazie per aver usato l'applicazione. Arrivederci!");
		}

		if (utente != null) {
			utente.menu(ristorante);
		}
	}
	
	public abstract void eseguiMetodi(Ristorante ristorante, int scelta);
}
