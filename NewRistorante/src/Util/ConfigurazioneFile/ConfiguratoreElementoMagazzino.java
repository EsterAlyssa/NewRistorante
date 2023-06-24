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

	public void setAttributiOggetto(String nomeOggetto, String nomeAttributo, String valoreAttributo) {
		ElementoMagazzino elementoMagazzino = new ElementoMagazzino(null, 0.0);
		setAttributiDatoOggetto(nomeAttributo, valoreAttributo, elementoMagazzino);
	}
	
	@Override
	public void setAttributiDatoOggetto(String nomeAttributo, String valoreAttributo, Object elementoMagazzino) {
		switch (nomeAttributo) {
		case "merce":
			Merce merce = null;
			ConfiguratoreMerce configuratoreMerce = new ConfiguratoreMerce();
	        String nomeAttributoSenzaPrefisso = nomeAttributo.substring(nomeAttributo.indexOf("=") + 1);
			configuratoreMerce.setAttributiDatoOggetto(nomeAttributoSenzaPrefisso, valoreAttributo, merce);
			((ElementoMagazzino) elementoMagazzino).setMerce(merce);
			break;
		case "quantita":
			((ElementoMagazzino) elementoMagazzino).setQuantita(Double.parseDouble(valoreAttributo));
			break;
		}
	}

	@Override
	public Object creaIstanzaOggetto(String nomeOggetto) {
		return new ElementoMagazzino(null, 0.0);
	}
}
