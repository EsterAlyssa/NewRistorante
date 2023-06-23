package Util.ConfigurazioneFile;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;

import Giorno.Giorno;
import Prenotazioni.Prenotazione;
import Prenotazioni.SceltaPrenotazione;

public class ConfiguratorePrenotazione extends ConfiguratoreManager{

	public ConfiguratorePrenotazione() {
		super();
	}

	void scriviParametriNelFile(Object prenotazione, BufferedWriter writer) {        
		try {
			writer.write("cliente=" + ((Prenotazione) prenotazione).getCliente());
			writer.newLine();
			writer.write("numCoperti=" + ((Prenotazione) prenotazione).getNumCoperti());
			writer.newLine();
			writer.write("data=" + ((Prenotazione) prenotazione).getData().toString());
			writer.newLine();

			HashMap<SceltaPrenotazione, Integer> elenco = ((Prenotazione) prenotazione).getElenco();
			writer.write("elenco=");
			ConfiguratoreManager confScelta = new ConfiguratoreSceltaPrenotazione();
			for (SceltaPrenotazione scelta : elenco.keySet()) {
				writer.write("quantitaPrenotate->" + elenco.get(scelta));
				confScelta.scriviParametriNelFile(scelta, writer);
				writer.append(';');
				writer.newLine();
			}
		} catch (IOException e) {
			System.out.println("Impossibile salvare l'oggetto prenotazione");
			e.printStackTrace();
		}
	}


	@Override
	public void setAttributiOggetto(String nomeOggetto, String nomeAttributo, String valoreAttributo) {
		Prenotazione prenotazione = new Prenotazione(nomeOggetto);
		// Imposta l'attributo nell'oggetto menu carta utilizzando i metodi setter corrispondenti
		switch (nomeAttributo) {
		case "cliente":
			prenotazione.setCliente(valoreAttributo);
			break;
		case "numCoperti":
			prenotazione.setNumCoperti(Integer.parseInt(valoreAttributo));
			break;
		case "data":
			prenotazione.setData(Giorno.parseGiorno(valoreAttributo));
			break;
		case "elenco":
			HashMap<SceltaPrenotazione, Integer> elenco = new HashMap<>();
			String[] sceltePrenotate = valoreAttributo.split(";\n");
			ConfiguratoreManager confScelta = new ConfiguratoreSceltaPrenotazione();
			for (String scelta : sceltePrenotate) {
				String[] coppia = scelta.split("->");
				String nomeScelta = coppia[0].trim();
				int numPersone = Integer.parseInt(coppia[1].trim());
				
				SceltaPrenotazione sceltaPren = null;
                confScelta.setAttributiOggetto(nomeScelta, nomeAttributo, valoreAttributo);

				elenco.put(sceltaPren, numPersone);
			}
			prenotazione.setElenco(elenco);
			break;
		}
	}
}
