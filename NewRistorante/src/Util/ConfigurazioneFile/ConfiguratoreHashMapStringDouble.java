package Util.ConfigurazioneFile;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;

public class ConfiguratoreHashMapStringDouble extends ConfiguratoreManager {

	
	public ConfiguratoreHashMapStringDouble() {
		super();
	}

	@Override
	void scriviParametriNelFile(Object oggettoExtra, BufferedWriter writer) {
		try {
			HashMap<String, Double> mappa = (HashMap<String, Double>) oggettoExtra;
			for (String nomeProdotto : mappa.keySet()) {
				writer.write(nomeProdotto + "->" + mappa.get(nomeProdotto));
				writer.append(';');
				writer.newLine();
			}
		} catch (IOException e) {
			System.out.println("Impossibile salvare l'oggetto mappa");
			e.printStackTrace();
		}
	}

	@Override
	public void setAttributiOggetto(String nomeOggetto, String nomeAttributo, String valoreAttributo) {
		HashMap<String, Double> mappa = new HashMap<>();
        mappa = setAttributiDatoOggetto(nomeOggetto, nomeAttributo, valoreAttributo, mappa);
	}
	
	public HashMap<String, Double> setAttributiDatoOggetto (String nomeOggetto, String nomeAttributo, String valoreAttributo, HashMap<String, Double> mappa) {
		String[] elementi = valoreAttributo.split(";\n");
        for (String elemento : elementi) {
            String[] coppia = elemento.split("->");
            String chiave = coppia[0].trim();
            double valore = Double.parseDouble(coppia[1].trim());
            mappa.put(chiave, valore);
        }
        return mappa;
	}


}
