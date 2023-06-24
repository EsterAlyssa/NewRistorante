package Util.ConfigurazioneFile;

import java.io.BufferedWriter;
import java.io.IOException;

import Giorno.Periodo;
import Ristorante.ElementiRistorante.Piatto;

public class ConfiguratorePiatto extends ConfiguratoreManager{
	
	public ConfiguratorePiatto() {
        super();
    }
	
	@Override
	void scriviParametriNelFile(Object piatto, BufferedWriter writer) {
		try {
			writer.write("nomePiatto="+((Piatto) piatto).getNome());
			writer.newLine();
			writer.write("caricoLavoroPiatto="+((Piatto) piatto).getCaricoLavoro());
			writer.newLine();
			writer.write("validitaPiatto="+((Piatto) piatto).getValidita().toString());
		} catch (IOException e) {
			System.out.println("Impossibile salvare l'oggetto piatto");
			e.printStackTrace();
		}
	}

	@Override
	public void setAttributiOggetto(String nomeOggetto, String nomeAttributo, String valoreAttributo) {
		Piatto piatto = new Piatto(nomeOggetto);
		// Imposta l'attributo nell'oggetto singleton utilizzando i metodi setter corrispondenti
		switch (nomeAttributo) {
		case "nomePiatto":
			piatto.setNome(valoreAttributo);
			break;
		case "caricoLavoroPiatto":
			piatto.setCaricoLavoro(Double.parseDouble(valoreAttributo));
			break;
		case "validitaPiatto":
			piatto.setValidita(Periodo.parsePeriodo(valoreAttributo));
			break;
		default:
			System.out.println("Errore nel settaggio dei parametri");
			break;
		}
	}


}
