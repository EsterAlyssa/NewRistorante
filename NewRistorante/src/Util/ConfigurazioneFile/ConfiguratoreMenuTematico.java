package Util.ConfigurazioneFile;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashSet;

import Ristorante.MenuCarta;
import Ristorante.MenuTematico;
import Ristorante.Periodo;
import Ristorante.Piatto;

public class ConfiguratoreMenuTematico extends ConfiguratoreManager {
	public ConfiguratoreMenuTematico() {
		super();
	}

	@Override
	void scriviParametriNelFile(Object menuTematico, BufferedWriter writer) {
		try {
			MenuTematico menu = (MenuTematico) menuTematico;
			writer.write("nome=" + menu.getNome());
			writer.newLine();
			writer.write("validita=" + menu.getValidita());
			writer.newLine();
			writer.write("caricoLavoro=" + menu.getCaricoLavoro());
			
			HashSet<Piatto> elenco = ((MenuTematico) menu).getElenco();
			writer.write("elenco=");
			ConfiguratorePiatto confPiat = new ConfiguratorePiatto();
			for (Piatto piatto : elenco) {
				confPiat.scriviParametriNelFile(piatto, writer);
			}
			
		} catch (IOException e) {
			System.out.println("Impossibile salvare l'oggetto menu tematico");
			e.printStackTrace();
		}
	}

	@Override
	public void setAttributiOggetto(String nomeOggetto, String nomeAttributo, String valoreAttributo) {
		MenuTematico menu = new MenuTematico(nomeOggetto);
		// Imposta l'attributo nell'oggetto menu tematico utilizzando i metodi setter corrispondenti
		switch (nomeAttributo) {
		case "nome":
			menu.setNome(valoreAttributo);
			break;
		case "validita":
			// Chiamata al metodo statico parsePeriodo per ottenere un oggetto di tipo Periodo
			menu.setValidita(Periodo.parsePeriodo(valoreAttributo));
			break;
		case "caricoLavoro":
			menu.setCaricoLavoro(Double.parseDouble(valoreAttributo));
			break;
		case "elenco":
			HashSet<Piatto> elenco = new HashSet<>();
			String[] piatti = valoreAttributo.split("\n");
			ConfiguratorePiatto confPiat = new ConfiguratorePiatto();
			for (String piatto : piatti) {
				Piatto p = new Piatto(piatto); //l'oggetto piatto ha nel attributo nome tutta la stringa
				int i=1;
				String nomePiatto = "Piatto"+i;
				confPiat.caricaIstanzaOggetto(nomePiatto, piatto);; //sovrascritto il nome dell'oggetto
				elenco.add(p);
				i++;
			}
			menu.setElenco(elenco);
			break;   
		default:
			System.out.println("Errore nel settaggio dei parametri");
			break;
		}
	}

}
