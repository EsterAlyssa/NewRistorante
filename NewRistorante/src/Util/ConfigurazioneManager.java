package Util;

import Ristorante.Ristorante;
import java.io.*;

public class ConfigurazioneManager {

	public static void salvaIstanzaRistorante(Ristorante ristorante, String pathRistorante) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathRistorante))) {
			// Scrittura dei parametri del ristorante nel file
			writer.write("nome="+ristorante.getNome());
			writer.newLine();
			writer.write("caricoLavoroPersona="+ristorante.getCaricoLavoroPersona());
			writer.newLine();
			writer.write("numPosti="+ristorante.getNumPosti());
			writer.newLine();
			writer.write("caricoLavoroRistorante="+ristorante.getCaricoLavoroRistorante());
		} catch (IOException e) {
			System.out.println("Impossibile salvare l'oggetto");
		}
	}

	public static void caricaIstanzaRistoranteDaFile(String pathRistorante) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(pathRistorante));
			String nomeRistorante = ServizioFile.getNomeFileSenzaEstensione(pathRistorante);
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split("=");
				if (parts.length == 2) {
					String attributeName = parts[0].trim();
					String attributeValue = parts[1].trim();
					// Imposta l'attributo corrispondente nell'oggetto
					setSingletonAttribute(nomeRistorante, pathRistorante, attributeName, attributeValue);
				}
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Impossibile caricare l'oggetto");
		}
	}

	private static void setSingletonAttribute(String nomeRistorante, String pathRistorante, String attributeName, String attributeValue) {
		Ristorante ristorante = Ristorante.getInstance(nomeRistorante, pathRistorante);
		// Imposta l'attributo nell'oggetto singleton utilizzando i metodi setter corrispondenti
		switch (attributeName) {
		case "nome":
			ristorante.setNome(attributeValue);
			break;
		case "caricoLavoroPersona":
			ristorante.setCaricoLavoroPersona(Integer.parseInt(attributeValue));
			break;
		case "numPosti":
			ristorante.setNumPosti(Integer.parseInt(attributeValue));
			break;
		case "caricoLavoroRistorante":
			ristorante.setCaricoLavoroRistorante(Double.parseDouble(attributeValue));
			break;
		default:
			System.out.println("Errore nel settaggio dei parametri");
			break;
		}
	}
}