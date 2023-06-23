package Util.ConfigurazioneFile;

import java.io.BufferedWriter;
import java.io.IOException;

import Ristorante.Periodo;
import Ristorante.Piatto;

public class ConfiguratorePiatto extends ConfiguratoreManager{
	
	public ConfiguratorePiatto() {
        super();
    }
	
	@Override
	void scriviParametriNelFile(Object piatto, BufferedWriter writer) {
		try {
			writer.write("nome="+((Piatto) piatto).getNome());
			writer.newLine();
			writer.write("caricoLavoro="+((Piatto) piatto).getCaricoLavoro());
			writer.newLine();
			writer.write("validita="+((Piatto) piatto).getValidita());
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
		case "nome":
			piatto.setNome(valoreAttributo);
			break;
		case "caricoLavoro":
			piatto.setCaricoLavoro(Double.parseDouble(valoreAttributo));
			break;
		case "validita":
			piatto.setValidita(Periodo.parsePeriodo(valoreAttributo));
			break;
		default:
			System.out.println("Errore nel settaggio dei parametri");
			break;
		}
	}


}
