package Util.ConfigurazioneFile;

import java.io.BufferedWriter;
import java.io.IOException;

import Ristorante.Ristorante;


public class ConfiguratoreRistorante extends ConfiguratoreManager {

	public ConfiguratoreRistorante() {
		super();
	}

	@Override
	void scriviParametriNelFile(Object ristorante, BufferedWriter writer){
		try {
			writer.write("nome="+((Ristorante) ristorante).getNome());
			writer.newLine();
			writer.write("caricoLavoroPersona="+((Ristorante) ristorante).getCaricoLavoroPersona());
			writer.newLine();
			writer.write("numPosti="+((Ristorante) ristorante).getNumPosti());
			writer.newLine();
			writer.write("caricoLavoroRistorante="+((Ristorante) ristorante).getCaricoLavoroRistorante());	
		} catch (IOException e) {
			System.out.println("Impossibile salvare l'oggetto ristorante");
			e.printStackTrace();
		}
	}

	@Override
	public void setAttributiOggetto(String nomeOggetto, String nomeAttributo, String valoreAttributo) {
		Ristorante ristorante = Ristorante.getInstance(nomeOggetto);
		// Imposta l'attributo nell'oggetto singleton utilizzando i metodi setter corrispondenti
		switch (nomeAttributo) {
		case "nome":
			ristorante.setNome(valoreAttributo);
			break;
		case "caricoLavoroPersona":
			ristorante.setCaricoLavoroPersona(Integer.parseInt(valoreAttributo));
			break;
		case "numPosti":
			ristorante.setNumPosti(Integer.parseInt(valoreAttributo));
			break;
		case "caricoLavoroRistorante":
			ristorante.setCaricoLavoroRistorante(Double.parseDouble(valoreAttributo));
			break;
		default:
			System.out.println("Errore nel settaggio dei parametri");
			break;
		}
	}


	

}
