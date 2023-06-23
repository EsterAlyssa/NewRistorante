package Main;

import Ristorante.Ristorante;
import Utenti.Gestore;
import Utenti.Utente;
import Util.InputDati;
import Util.ServizioFile;
import Util.ConfigurazioneFile.ConfiguratoreManager;
import Util.ConfigurazioneFile.ConfiguratoreRistorante;

public class Main {

	private static final String RISTORANTE_DIRECTORY_PATH = "./FileRistorante/";
	private static Ristorante ristorante;

	public static void main(String[] args) {
		if (ServizioFile.ePrimaApertura(RISTORANTE_DIRECTORY_PATH)) {
			ristorante = creaRistorante();
		} else {
			ristorante = accediRistorante();
		}
		Utente.mostraMenuRuoli(RISTORANTE_DIRECTORY_PATH);

	}

	private static Ristorante creaRistorante() {
		String messaggioBenvenuto = "Benvenuto! Inserisci il nome del ristorante: ";
		String nomeRistorante = InputDati.leggiStringaNonVuota(messaggioBenvenuto);
		String percorsoCompleto = RISTORANTE_DIRECTORY_PATH + nomeRistorante + ".txt";
		Ristorante ristorante = Ristorante.getInstance(nomeRistorante);
		Gestore.inizializzaRistorante(ristorante);
		ConfiguratoreManager configurazioneManager = new ConfiguratoreRistorante();
		configurazioneManager.salvaIstanzaOggetto(ristorante, percorsoCompleto);
		return ristorante;
	}

	private static Ristorante accediRistorante() {
		Ristorante ristoranteTrovato = null;
		String nomeRistorante = ServizioFile.getNomeFile(RISTORANTE_DIRECTORY_PATH);
		if (nomeRistorante != null) {
			String percorsoCompleto = RISTORANTE_DIRECTORY_PATH + nomeRistorante + ".txt";
			ConfiguratoreManager configurazioneManager = new ConfiguratoreRistorante();
			configurazioneManager.caricaIstanzaOggettoDaFile(percorsoCompleto);
			ristoranteTrovato = Ristorante.getInstance(nomeRistorante);
			System.out.println("Benvenuto! Ristorante: " + ristoranteTrovato.getNome());
		} 
		return ristoranteTrovato;
	}
}