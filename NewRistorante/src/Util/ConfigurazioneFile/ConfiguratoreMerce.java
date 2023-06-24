package Util.ConfigurazioneFile;

import java.io.BufferedWriter;
import java.io.IOException;
import Magazzino.Merce.*;

public class ConfiguratoreMerce extends ConfiguratoreManager {

	public ConfiguratoreMerce() {
		super();
	}

	@Override
	void scriviParametriNelFile(Object oggetto, BufferedWriter writer) {
		try {
			Merce merce = (Merce) oggetto;
			if (merce instanceof Ingrediente) {
				merce = (Ingrediente) merce;
			} else if (merce instanceof Bevanda) {
				merce = (Bevanda) merce;
			} else if(merce instanceof GenereExtra) {
				merce = (GenereExtra) merce;
			} else {
				merce = null; 
			}

			if (merce != null) {
				writer.write(merce.getClass().getName());
				writer.newLine();
				writer.write("nomeMerce=" + merce.getNome());
				writer.newLine();
				writer.write("unitaMisura=" + merce.getUnitaMisura());
				writer.newLine();
				writer.write("scadenza=" + merce.getScadenza().toString());
				writer.newLine();
				writer.write("qualita=" + merce.getQualita());
			}
		} catch (IOException e) {
			System.out.println("Impossibile salvare l'oggetto merce");
			e.printStackTrace();
		}
	}

	@Override
	public void setAttributiOggetto(String nomeOggetto, String nomeAttributo, String valoreAttributo) {
		Merce merce = null;
		merce = setAttributiDatoOggetto (nomeOggetto, nomeAttributo, valoreAttributo, merce);
	}

	public Merce setAttributiDatoOggetto(String nomeOggetto, String nomeAttributo, String valoreAttributo, Merce merce) {
		switch (nomeOggetto) {
		case "Ingrediente":
			merce = new Ingrediente(nomeOggetto, ""); // Crea un'istanza di Ingrediente vuota
			break;
		case "Bevanda":
			merce = new Bevanda(nomeOggetto, 0.0); // Crea un'istanza di Bevanda vuota
			break;
		case "GenereExtra":
			merce = new GenereExtra(nomeOggetto, 0.0); // Crea un'istanza di GenereExtra vuota
			break;
		default:
			System.out.println("Tipo di merce non riconosciuto");
			break;
		}
		if (merce != null) {
	        switch (nomeAttributo) {
	            case "nomeMerce":
	                merce.setNome(valoreAttributo);
	                break;
	            case "unitaMisura":
	                merce.setUnitaMisura(valoreAttributo);
	                break;
	            case "scadenza":
	                merce.setScadenza(Giorno.Giorno.parseGiorno(valoreAttributo));
	                break;
	            case "qualita":
	                merce.setQualita(Boolean.parseBoolean(valoreAttributo));
	                break;
	            default:
	                System.out.println("Attributo non riconosciuto");
	                break;
	        }
	    }
		return merce;
	}

}
