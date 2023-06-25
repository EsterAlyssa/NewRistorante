package Utenti;

import java.util.HashMap;
import java.util.HashSet;

import Giorno.Periodo;
import Ristorante.Ristorante;
import Ristorante.ElementiRistorante.MenuTematico;
import Ristorante.ElementiRistorante.Piatto;
import Ristorante.ElementiRistorante.Ricetta;
import Util.InputDati;
import Util.ServizioFile;
import Util.ConfigurazioneFile.*;

public class Gestore extends Utente{

	private static String etichettaG = "gestore";
	private static String[] voci = {"Cambia i parametri del ristorante","Visualizza i parametri del ristorante","Aggiungi bevanda all'insieme delle bevande",
			"Rimuovi bevanda dall'insieme delle bevande", "Visualizza l'insieme delle bevande", "Aggiungi genere extra all'insieme dei generi extra",
			"Rimuovi genere extra dall'insieme dei generi extra", "Visualizza l'insieme dei generi extra","Crea corrispondenza Piatto - Ricetta",
			"Crea periodo di validita' di tutti i piatti", "Visualizza tutti i piatti", "Verifica l'esistenza di una ricetta",
			"Crea una ricetta", "Visualizza il ricettario (solo i nomi)", "Visualizza una ricetta", "Visualizza le informazioni di tutte le ricetta",
			"Crea un menu tematico", "Visualizza tutti i menu tematici (solo i nomi)", "Visualizza un menu tematico"};

	public Gestore(String nome) {
		super(nome, etichettaG, voci);
	}

	public static void inizializzaRistorante(String pathCompletoFile) {
		String msgCarico = "Inserisci il carico di lavoro per persona: ";
		String msgNumPosti = "Inserisci il numero di posti a sedere disponibili del ristorante: ";

		int caricoLavoroPersona = InputDati.leggiInteroNonNegativo(msgCarico);
		int numPosti = InputDati.leggiInteroPositivo(msgNumPosti);

		ConfiguratoreRistorante conf = new ConfiguratoreRistorante();
		Ristorante ristorante = (Ristorante) conf.caricaIstanzaOggettoDaFile(pathCompletoFile);
		
		ristorante.setCaricoLavoroPersona(caricoLavoroPersona);
		ristorante.setNumPosti(numPosti);
		ristorante.setCaricoLavoroRistorante(1.2 * (ristorante.getCaricoLavoroPersona() * ristorante.getNumPosti()));
		
		conf.salvaIstanzaOggetto(ristorante, pathCompletoFile);
	}

	public void visualizzaRistorante(String pathCompletoFile) {
		ConfiguratoreRistorante conf = new ConfiguratoreRistorante();
		Ristorante ristorante = (Ristorante) conf.caricaIstanzaOggettoDaFile(pathCompletoFile);
		
		System.out.printf("Nome del ristorante: %s\n", ristorante.getNome());
		System.out.printf("Numeri di posti a sedere nel ristorante: %d\n", ristorante.getNumPosti());
		System.out.printf("Carico di lavoro per persona: %d\n", ristorante.getCaricoLavoroPersona());
		System.out.printf("Carico di lavoro sostenibile dal ristorante: %.2f\n", ristorante.getCaricoLavoroRistorante());
	}

	public void aggiungiBevanda(String pathCompletoFile) {
		ConfiguratoreRistorante conf = new ConfiguratoreRistorante();
		Ristorante ristorante = (Ristorante) conf.caricaIstanzaOggettoDaFile(pathCompletoFile);
		
		String pathDirectory = pathCompletoFile.substring(0, pathCompletoFile.lastIndexOf("/"));
		String nomeDirectory = "Insiemi extra";
		String pathInsiemiExtra = pathDirectory + "/" + nomeDirectory;

	    // Controlla se la directory "Insiemi extra" esiste, altrimenti la crea
	    ServizioFile.creaDirectory(pathInsiemiExtra);
	    
		String nomeFileBevande = "insieme bevande.txt";
		String pathFileBevande = pathInsiemiExtra + "/" + nomeFileBevande;
		
		// Controlla se il file "insieme_bevande.txt" esiste, altrimenti lo crea
	    if (!ServizioFile.controlloEsistenzaFile(pathFileBevande)) {
	        ServizioFile.creaFile(pathFileBevande);
	    }
		
		String msgNome = "Inserisci il nome della bevanda da aggiungere: ";
		String msgConsumo = "Inserisci il consumo pro capite della bevanda da aggiungere: ";

		String nome = InputDati.leggiStringaNonVuota(msgNome);
		double consumoProCapite = InputDati.leggiDoubleConMinimo(msgConsumo, 0);

		ristorante.aggiungiBevanda(nome, consumoProCapite);
		
		ConfiguratoreExtra confIns = new ConfiguratoreExtra();
		confIns.salvaIstanzaOggetto(ristorante.getInsiemeB(), pathFileBevande);
	}

	public void rimuoviBevanda(String pathCompletoFile) {
		ConfiguratoreRistorante conf = new ConfiguratoreRistorante();
		Ristorante ristorante = (Ristorante) conf.caricaIstanzaOggettoDaFile(pathCompletoFile);
		
		String msgNome = "Inserisci il nome della bevanda da rimuovere: ";

		String nome = InputDati.leggiStringaNonVuota(msgNome);

		ristorante.rimuoviBevanda(nome);
		
		String pathDirectory = pathCompletoFile.substring(0, pathCompletoFile.lastIndexOf("/"));
		String nomeDirectory = "Insiemi extra";
		String pathInsiemiExtra = pathDirectory + "/" + nomeDirectory;
		String nomeFileBevande = "insieme bevande.txt";
		String pathFileBevande = pathInsiemiExtra + "/" + nomeFileBevande;
		
		ConfiguratoreExtra confIns = new ConfiguratoreExtra();
		confIns.salvaIstanzaOggetto(ristorante.getInsiemeB(), pathFileBevande);
	}

	public void visualizzaInsiemeBevande(String pathCompletoFile) {
		ConfiguratoreRistorante conf = new ConfiguratoreRistorante();
		Ristorante ristorante = (Ristorante) conf.caricaIstanzaOggettoDaFile(pathCompletoFile);
		
		String pathDirectory = pathCompletoFile.substring(0, pathCompletoFile.lastIndexOf("/"));
		String nomeDirectory = "Insiemi extra";
		String pathInsiemiExtra = pathDirectory + "/" + nomeDirectory;
		String nomeFileBevande = "insieme bevande.txt";
		String pathFileBevande = pathInsiemiExtra + "/" + nomeFileBevande;
		
		ConfiguratoreExtra confIns = new ConfiguratoreExtra();
		HashMap<String, Double> insiemeB = ((HashMap<String, Double>) confIns.caricaIstanzaOggettoDaFile(pathFileBevande));
		
		for (String elemento : insiemeB.keySet()) {
			System.out.printf("bevanda: %s\tconsumo pro capite: %f.2\n", elemento, insiemeB.get(elemento));
		}
	}

	public void aggiungiGenereExtra(String pathCompletoFile) {
		ConfiguratoreRistorante conf = new ConfiguratoreRistorante();
		Ristorante ristorante = (Ristorante) conf.caricaIstanzaOggettoDaFile(pathCompletoFile);
		
		String msgNome = "Inserisci il nome del genere extra da aggiungere: ";
		String msgConsumo = "Inserisci il consumo pro capite del genere extra da aggiungere: ";

		String nome = InputDati.leggiStringaNonVuota(msgNome);
		double consumoProCapite = InputDati.leggiDoubleConMinimo(msgConsumo, 0);

		ristorante.aggiungiGenereExtra(nome, consumoProCapite);
	}

	public void rimuoviGenereExtra(String pathCompletoFile) {
		ConfiguratoreRistorante conf = new ConfiguratoreRistorante();
		Ristorante ristorante = (Ristorante) conf.caricaIstanzaOggettoDaFile(pathCompletoFile);
		
		String msgNome = "Inserisci il nome del genere extra da rimuovere: ";

		String nome = InputDati.leggiStringaNonVuota(msgNome);

		ristorante.rimuoviGenereExtra(nome);
	}

	public void visualizzaInsiemeGeneriExtra(String pathCompletoFile) {
		ConfiguratoreRistorante conf = new ConfiguratoreRistorante();
		Ristorante ristorante = (Ristorante) conf.caricaIstanzaOggettoDaFile(pathCompletoFile);
		
		for (String elemento : ristorante.getInsiemeGE().keySet()) {
			System.out.printf("genere extra: %s\tconsumo pro capite: %f.2\n", elemento, ristorante.getInsiemeGE().get(elemento));
		}
	}

	public void corrispondenzaPiattoRicetta (String pathCompletoFile) {
		ConfiguratoreRistorante conf = new ConfiguratoreRistorante();
		Ristorante ristorante = (Ristorante) conf.caricaIstanzaOggettoDaFile(pathCompletoFile);
		
		String msgValidita = "Vuoi gia' inserire il periodo di validita' per ogni piatto? [S/N] ";

		for (Ricetta ricetta : ristorante.getRicettario()) {
			Piatto piatto = new Piatto (ricetta.getNome(), ricetta.getCaricoLavoroPorzione());

			boolean scelta = InputDati.yesOrNo(msgValidita);
			if (scelta) {
				aggiungiValiditaPiatto(piatto);
			}

			ristorante.aggiungiPiatto(piatto);;
		}
	}

	private void aggiungiValiditaPiatto (Piatto piatto) {
		String msgValidita = "Inserisci il periodo di validita' del piatto: ";
		System.out.println(msgValidita);

		Periodo validita = new Periodo();
		validita.creaPeriodoValidita();
		piatto.setValidita(validita);
	}

	public void aggiungiValiditaPiatti (String pathCompletoFile) {
		ConfiguratoreRistorante conf = new ConfiguratoreRistorante();
		Ristorante ristorante = (Ristorante) conf.caricaIstanzaOggettoDaFile(pathCompletoFile);
		HashSet<Piatto> piatti = ristorante.getPiatti();
		// da pathCompletoFile ricavare ristorante e quindi l'hashet<Piatto>
		for (Piatto piatto : piatti) {	
			aggiungiValiditaPiatto(piatto);
		}
	}

	public void verificaEsistenzaRicetta(String pathCompletoFile){
		ConfiguratoreRistorante conf = new ConfiguratoreRistorante();
		Ristorante ristorante = (Ristorante) conf.caricaIstanzaOggettoDaFile(pathCompletoFile);
		
		String msgNome = "Inserisci il nome del piatto da cercare: ";
		String msgSiRicetta = "Esiste una corrispondenza tra il piatto cercato e una ricetta";
		String msgNoRicetta = "Non esiste una ricetta con questo nome";

		String nome = InputDati.leggiStringaNonVuota(msgNome);

		try {
			Ricetta.trovaRicetta(nome, ristorante.getRicettario());
			System.out.println(msgSiRicetta);
		} catch (NullPointerException e) {
			System.out.println(msgNoRicetta);
		}
	}

	public void visualizzaPiatti(String pathCompletoFile) {
		ConfiguratoreRistorante conf = new ConfiguratoreRistorante();
		Ristorante ristorante = (Ristorante) conf.caricaIstanzaOggettoDaFile(pathCompletoFile);
		
		HashSet<Piatto> piatti = ristorante.getPiatti();
		
		for (Piatto piatto : piatti) {
			System.out.printf("nome piatto: %s\tperiodo di validita': %s\n", piatto.getNome(), piatto.getValidita().toString());
		}
	}

	public void creaRicetta(String pathCompletoFile) {
		ConfiguratoreRistorante conf = new ConfiguratoreRistorante();
		Ristorante ristorante = (Ristorante) conf.caricaIstanzaOggettoDaFile(pathCompletoFile);
		
		String msgNome = "Inserisci il nome della ricetta da creare: ";
		String msgNumPorzioni = "Inserisci il numero delle porzioni della ricetta da creare: ";
		String msgCaricoLavoro = "Inserisci il carico di lavoro per persona della ricetta da creare: ";

		String msgScelta = "Vuoi inserire altri ingredienti?[S/N] ";

		String nomeRicetta = InputDati.leggiStringaNonVuota(msgNome);
		int numPorzioni = InputDati.leggiInteroPositivo(msgNumPorzioni);
		double caricoLavoroPorzione = InputDati.leggiDoubleConMinimo(msgCaricoLavoro, 0);

		Ricetta nuova = new Ricetta(nomeRicetta, numPorzioni, caricoLavoroPorzione);
		boolean scelta = true;
		do {
			aggiungiIngredienti(nuova);
			scelta = InputDati.yesOrNo(msgScelta);
		} while (scelta);
		ristorante.aggiungiRicetta(nuova);
	}

	private void aggiungiIngredienti(Ricetta ricetta) {
		String msgNome = "Inserisci il nome dell'ingrediente da aggiungere: ";
		String msgDose = "Inserisci la dose dell'ingrediente da aggiungere: ";

		String nomeIngrediente = InputDati.leggiStringaNonVuota(msgNome);
		double doseIngrediente = InputDati.leggiDoubleConMinimo(msgDose, 0);

		ricetta.aggiungiIngrediente(nomeIngrediente, doseIngrediente);		
	}

	public void visualizzaRicettario(String pathCompletoFile) {
		ConfiguratoreRistorante conf = new ConfiguratoreRistorante();
		Ristorante ristorante = (Ristorante) conf.caricaIstanzaOggettoDaFile(pathCompletoFile);
		
		int i = 1;
		for (Ricetta ric : ristorante.getRicettario()) {
			System.out.printf("%d: %s\n", i, ric.getNome());
			i++;
		}
	}

	public void visualizzaRicetta(String pathCompletoFile) {
		ConfiguratoreRistorante conf = new ConfiguratoreRistorante();
		Ristorante ristorante = (Ristorante) conf.caricaIstanzaOggettoDaFile(pathCompletoFile);
	
		String msgRichiestaRicetta = "Inserisci il nome della ricetta da visualizzare: ";

		String msgNoRicetta = "Non esiste una ricetta con questo nome. Inseriscilo di nuovo: ";

		visualizzaRicettario(pathCompletoFile);

		String ricettaScelta = InputDati.leggiStringaNonVuota(msgRichiestaRicetta);
		boolean trovata = true;
		do {
			Ricetta ricettaTrovata = Ricetta.trovaRicetta(ricettaScelta, ristorante.getRicettario());
			if (ricettaTrovata != null) {
				//ritornare la ricetta
				ricettaTrovata.toString();
				trovata = false;
			} else {
				//la ricetta non è nel ricettario
				System.out.println(msgNoRicetta);
			}
		} while (trovata);
	}

	public void visualizzaInfoRicette (String pathCompletoFile) {
		ConfiguratoreRistorante conf = new ConfiguratoreRistorante();
		Ristorante ristorante = (Ristorante) conf.caricaIstanzaOggettoDaFile(pathCompletoFile);
	
		for (Ricetta ric : ristorante.getRicettario()) {
			System.out.println(ric.toString());
		}
	}

	//in ristorante abbiamo calendario, dove abbiamo giornate, aggiungiamo i menu alle rispettive giornate
	public void creaMenuTematico(String pathCompletoFile) {
		ConfiguratoreRistorante conf = new ConfiguratoreRistorante();
		Ristorante ristorante = (Ristorante) conf.caricaIstanzaOggettoDaFile(pathCompletoFile);
		
		String msgNome = "Inserisci il nome del menu tematico da creare: ";
		String msgPiatto = "Inserisci il nome del piatto da aggiungere al menu tematico: ";
		String msgErrValidita = "ATTENZIONE! Il piatto scelto non è valido per i giorni selezionati.";
		String msgErrPiatto = "ATTENZIONE! Con questo piatto il carico di lavoro è troppo alto.";
		String msgScelta = "Vuoi inserire altri piatti?[S/N] ";


		String nomeMenuT = InputDati.leggiStringaNonVuota(msgNome);

		Periodo validitaMenuT = new Periodo();
		validitaMenuT.creaPeriodoValidita();

		MenuTematico nuovo = new MenuTematico(nomeMenuT, validitaMenuT);

		boolean scelta = true;
		do {
			String nomePiatto = InputDati.leggiStringaNonVuota(msgPiatto);
			Piatto piattoTrovato = Piatto.trovaPiattoDaNome(nomePiatto, ristorante.getPiatti());
			if (piattoTrovato != null) {
				double CLP = piattoTrovato.getCaricoLavoro();
				double CLM = nuovo.getCaricoLavoro();
				double CLPersona = ristorante.getCaricoLavoroPersona();
				if ((CLP+CLM) <= (4/3)*CLPersona) {
					if (validitaMenuT.getPeriodoValidita().containsAll(piattoTrovato.getValidita().getPeriodoValidita())) {
						nuovo.aggiungiPiatto(piattoTrovato);
						scelta = InputDati.yesOrNo(msgScelta);
					} else {
						System.out.println(msgErrValidita);
					}
				} else {
					System.out.println(msgErrPiatto);
				}
			}
		} while (scelta);

		ristorante.aggiungiMenuTematico(nuovo);
	}

	public void visualizzaMenuTematici(String pathCompletoFile) {
		ConfiguratoreRistorante conf = new ConfiguratoreRistorante();
		Ristorante ristorante = (Ristorante) conf.caricaIstanzaOggettoDaFile(pathCompletoFile);
	
		for (MenuTematico menu : ristorante.getMenuTematici()) {
			System.out.println(menu.toString());
		}
	}	

	private void visualizzaNomiMenuTematici(Ristorante ristorante) {
		int i = 1;
		for (MenuTematico menu : ristorante.getMenuTematici()) {
			System.out.printf("%d) %s", i, menu.getNome());
			i++;
		}
	}

	//dato un giorno chiesto nel metodo stesso
	public void visualizzaMenuTematico(String pathCompletoFile) {
		ConfiguratoreRistorante conf = new ConfiguratoreRistorante();
		Ristorante ristorante = (Ristorante) conf.caricaIstanzaOggettoDaFile(pathCompletoFile);
		
		String msgRichiesta = "Inserisci il nome del menu tematico da visualizzare: ";
		String msgErrMenu= "ATTENZIONE! Il menu inserito non esiste";

		visualizzaNomiMenuTematici(ristorante);

		boolean trovato = true;
		do {
			String ricerca = InputDati.leggiStringaNonVuota(msgRichiesta);	
			MenuTematico menu = MenuTematico.trovaMenuTDaNome(ricerca, ristorante.getMenuTematici());
			if (menu != null) {
				System.out.println(menu.toString());
				trovato = false;
			} else {
				System.out.println(msgErrMenu);
			}
		} while (trovato);

	}

	@Override
	public void eseguiMetodi(int scelta, String pathCompletoFile) {
		switch (scelta) {
		case 1:
			inizializzaRistorante(pathCompletoFile);
			break;
		case 2:
			visualizzaRistorante(pathCompletoFile);
			break;
		case 3:
			aggiungiBevanda(pathCompletoFile);
			break;
		case 4:
			rimuoviBevanda(pathCompletoFile);
			break;
		case 5:
			visualizzaInsiemeBevande(pathCompletoFile);
			break;
		case 6: 
			aggiungiGenereExtra(pathCompletoFile);
			break;
		case 7: 
			rimuoviGenereExtra(pathCompletoFile);
			break;
		case 8: 
			visualizzaInsiemeGeneriExtra(pathCompletoFile);
			break;
		case 9: 
			corrispondenzaPiattoRicetta(pathCompletoFile);
			break;
		case 10:
			aggiungiValiditaPiatti(pathCompletoFile); //aggiungere piatto al menu alla carta di quel giorno
			break;
		case 11:
			visualizzaPiatti(pathCompletoFile);
			break;
		case 12:
			verificaEsistenzaRicetta(pathCompletoFile);
			break;
		case 13: 
			creaRicetta(pathCompletoFile);
			break;
		case 14:
			visualizzaRicettario(pathCompletoFile);
			break;
		case 15: 
			visualizzaRicetta(pathCompletoFile);
			break;
		case 16:
			visualizzaInfoRicette(pathCompletoFile);
			break;
		case 17:
			creaMenuTematico(pathCompletoFile); 
			break;
		case 18:
			visualizzaMenuTematici(pathCompletoFile);
			break;
		case 19:
			visualizzaMenuTematico(pathCompletoFile); 
			break;
		}
	}

}
