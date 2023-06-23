package Util.ConfigurazioneFile;

import java.io.BufferedWriter;
import Prenotazioni.SceltaPrenotazione;
import Ristorante.ElementiRistorante.MenuTematico;
import Ristorante.ElementiRistorante.Piatto;

public class ConfiguratoreSceltaPrenotazione extends ConfiguratoreManager {
	
	public ConfiguratoreSceltaPrenotazione() {
        super();
    }

	@Override
	void scriviParametriNelFile(Object sceltaPrenotazione, BufferedWriter writer) {
		SceltaPrenotazione sceltaPren = (SceltaPrenotazione) sceltaPrenotazione;
		if (sceltaPren instanceof MenuTematico) {
			ConfiguratoreManager confMenuT = new ConfiguratoreMenuTematico();
			confMenuT.scriviParametriNelFile(sceltaPren, writer);
		};
		
		if (sceltaPren instanceof Piatto) {
			ConfiguratoreManager confPiat = new ConfiguratorePiatto();
			confPiat.scriviParametriNelFile(sceltaPren, writer);
		}
		else {
			System.out.println("Errore! L'oggetto non è una possibile scelta per le prenotazioni");
		}
		
	}

	@Override
	public void setAttributiOggetto(String nomeOggetto, String nomeAttributo, String valoreAttributo) {
		if (nomeAttributo.contains("Piatto")) {
			ConfiguratoreManager confPiat = new ConfiguratorePiatto();
			confPiat.setAttributiOggetto(nomeOggetto, nomeAttributo, valoreAttributo);
		} else {
			ConfiguratoreManager confMenuT = new ConfiguratoreMenuTematico();
			confMenuT.setAttributiOggetto(nomeOggetto, nomeAttributo, valoreAttributo);
		}
		
	}
}
