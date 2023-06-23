package Util.ConfigurazioneFile;

import Util.ServizioFile;

import java.io.*;

public abstract class ConfiguratoreManager {

	public void salvaIstanzaOggetto(Object oggetto, String pathRistorante) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathRistorante))) {
			// Scrittura dei parametri del ristorante nel file
			scriviParametriNelFile(oggetto, writer);
		} catch (IOException e) {
			System.out.println("Impossibile salvare l'oggetto");
		}
	}

	abstract void scriviParametriNelFile(Object oggetto, BufferedWriter writer);

	public void caricaIstanzaOggettoDaFile(String pathOggetto) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(pathOggetto));
			String nomeOggetto = ServizioFile.getNomeFileSenzaEstensione(pathOggetto);
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parte = line.split("=");
				if (parte.length == 2) {
					String nomeAttributo = parte[0].trim();
					String valoreAttributo = parte[1].trim();
					// Imposta l'attributo corrispondente nell'oggetto
					setAttributiOggetto(nomeOggetto, nomeAttributo, valoreAttributo);
				}
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Impossibile caricare l'oggetto");
		}
	}

	public abstract void setAttributiOggetto(String nomeOggetto, String nomeAttributo, String valoreAttributo);

}