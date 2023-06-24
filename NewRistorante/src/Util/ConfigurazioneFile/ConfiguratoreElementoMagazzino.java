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
			ElementoMagazzino elementoMagazzino = (ElementoMagazzino) oggetto;
			Merce merce = elementoMagazzino.getMerce();

			ConfiguratoreMerce configuratoreMerce = new ConfiguratoreMerce();
			configuratoreMerce.scriviParametriNelFile(merce, writer);

			writer.write("quantita=" + elementoMagazzino.getQuantita());
			writer.newLine();
		} catch (IOException e) {
			System.out.println("Impossibile salvare l'oggetto Elemento Magazzino");
			e.printStackTrace();
		}
	}

	@Override
	public void setAttributiOggetto(String nomeOggetto, String nomeAttributo, String valoreAttributo) {
		ElementoMagazzino elementoMagazzino = new ElementoMagazzino(null, 0.0);
		switch (nomeAttributo) {
		case "merce":
			Merce merce = null;
			ConfiguratoreMerce configuratoreMerce = new ConfiguratoreMerce();
	        String nomeAttributoSenzaPrefisso = nomeAttributo.substring(nomeAttributo.indexOf("=") + 1);
			merce = configuratoreMerce.setAttributiDatoOggetto(nomeOggetto, nomeAttributoSenzaPrefisso, valoreAttributo, merce);
			elementoMagazzino.setMerce(merce);
			break;
		case "quantita":
			elementoMagazzino.setQuantita(Double.parseDouble(valoreAttributo));
			break;
		}

	}


}
