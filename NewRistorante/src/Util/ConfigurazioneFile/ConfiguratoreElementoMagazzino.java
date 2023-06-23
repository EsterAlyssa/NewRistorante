package Util.ConfigurazioneFile;

import java.io.BufferedWriter;
import java.io.IOException;

import Magazzino.ElementoMagazzino;
import Magazzino.Merce.Merce;

public class ConfiguratoreElementoMagazzino extends ConfiguratoreManager{

	public ConfiguratoreElementoMagazzino() {
		super();
	}

	@Override
	void scriviParametriNelFile(Object oggetto, BufferedWriter writer) {
		try {
			writer.write("merce=");
			writer.newLine();
			ConfiguratoreManager confMerce = new ConfiguratoreMerce();
			Merce merce = ((ElementoMagazzino) oggetto ).getMerce();
			confMerce.scriviParametriNelFile(merce, writer);
			writer.newLine();
			writer.write("quantita="+((ElementoMagazzino) oggetto).getQuantita());
			writer.newLine();
		} catch (IOException e) {
			System.out.println("Impossibile salvare l'oggetto piatto");
			e.printStackTrace();
		}
	}

	@Override
	public void setAttributiOggetto(String nomeOggetto, String nomeAttributo, String valoreAttributo) {
		// TODO Auto-generated method stub

	}


}
