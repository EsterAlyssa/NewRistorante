package Util.ConfigurazioneFile;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;

import Magazzino.ListaSpesa;

public class ConfiguratoreListaSpesa extends ConfiguratoreManager{

	public ConfiguratoreListaSpesa() {
		super();
	}

	@Override
	void scriviParametriNelFile(Object listaSpesa, BufferedWriter writer) {
		try {
			HashMap<String, Double> lista = ((ListaSpesa) listaSpesa).getLista();
			writer.write("lista=");
			ConfiguratoreManager conf = new ConfiguratoreHashMapStringDouble();
			conf.scriviParametriNelFile(lista, writer);
			writer.newLine();
		} catch (IOException e) {
			System.out.println("Impossibile salvare l'oggetto lista spesa");
			e.printStackTrace();
		}
	}

	@Override
	public void setAttributiOggetto(String nomeOggetto, String nomeAttributo, String valoreAttributo) {
		ListaSpesa lista = new ListaSpesa();

		switch (nomeAttributo) {
		case "lista":
			HashMap<String, Double> mapLista = new HashMap<>();
			ConfiguratoreHashMapStringDouble conf = new ConfiguratoreHashMapStringDouble();
			conf.setAttributiDatoOggetto(nomeOggetto, valoreAttributo, valoreAttributo, mapLista);
			lista.setLista(mapLista);
			break;
		default:
			System.out.println("Attributo non riconosciuto: " + nomeAttributo);
			break;
		}
	}
}