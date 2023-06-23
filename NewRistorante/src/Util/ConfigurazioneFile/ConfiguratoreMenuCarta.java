package Util.ConfigurazioneFile;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashSet;

import Ristorante.MenuCarta;
import Ristorante.Periodo;
import Ristorante.Piatto;

public class ConfiguratoreMenuCarta extends ConfiguratoreManager {

	public ConfiguratoreMenuCarta() {
		super();
	}

	@Override
	void scriviParametriNelFile(Object menuCarta, BufferedWriter writer) {
		try {
			MenuCarta menu = (MenuCarta) menuCarta;
			writer.write("validita=" + menu.getValidita());

			HashSet<Piatto> elenco = ((MenuCarta) menu).getElenco();
			writer.write("elenco=");
			ConfiguratorePiatto confPiat = new ConfiguratorePiatto();
			for (Piatto piatto : elenco) {
				confPiat.scriviParametriNelFile(piatto, writer);
			}
		} catch (IOException e) {
			System.out.println("Impossibile salvare l'oggetto menu carta");
			e.printStackTrace();
		}
	}

	@Override
	public void setAttributiOggetto(String nomeOggetto, String nomeAttributo, String valoreAttributo) {
		MenuCarta menu = new MenuCarta();
		// Imposta l'attributo nell'oggetto menu carta utilizzando i metodi setter corrispondenti
		switch (nomeAttributo) {
		case "elenco":
			HashSet<Piatto> elenco = new HashSet<>();
			String[] piatti = valoreAttributo.split("\n");
			for (String piatto : piatti) {
				ConfiguratorePiatto confPiat = new ConfiguratorePiatto();
				Piatto p = new Piatto(piatto);
				confPiat.caricaIstanzaOggettoDaFile(piatto);
				elenco.add(p);
			}
			menu.setElenco(elenco);
			break;    
		case "validita":
			// Chiamata al metodo statico parsePeriodo per ottenere un oggetto di tipo Periodo
			menu.setValidita(Periodo.parsePeriodo(valoreAttributo));
			break;
		default:
			System.out.println("Errore nel settaggio dei parametri");
			break;
		}
	}
}
