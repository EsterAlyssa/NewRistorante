package Main;

import Ristorante.Ristorante;
import Utenti.Gestore;
import Utenti.Utente;
import Util.InputDati;
import Util.ServizioFile;
import Util.ConfigurazioneFile.ConfiguratoreRistorante;

public class Main {

	private static final String appDirectoryPath = "./FileRistorante/";
	private static Ristorante ristorante;

	public static void main(String[] args) {
		if (ServizioFile.ePrimaApertura(appDirectoryPath)) {
			ristorante = creaRistorante();
		} else {
			ristorante = accediRistorante(appDirectoryPath);
		}
		Utente.mostraMenuRuoli(appDirectoryPath+ristorante.getNome()+".txt");
	}

	private static Ristorante creaRistorante() {
		String messaggioBenvenuto = "Benvenuto! Inserisci il nome del ristorante: ";
		String nomeRistorante = InputDati.leggiStringaNonVuota(messaggioBenvenuto);
		String percorsoCompleto = appDirectoryPath+nomeRistorante + ".txt";
		Ristorante ristorante = Ristorante.getInstance(nomeRistorante);
		ConfiguratoreRistorante conf = new ConfiguratoreRistorante();
		conf.salvaIstanzaOggetto(ristorante, percorsoCompleto);
		Gestore.inizializzaRistorante(percorsoCompleto);
		conf.salvaIstanzaOggetto(ristorante, percorsoCompleto);
		return ristorante;
	}

	public static Ristorante accediRistorante(String appDirectoryPath) {
		Ristorante ristoranteTrovato = null;
		String nomeRistorante = ServizioFile.trovaPrimoFileTxt(appDirectoryPath);
		if (nomeRistorante != null) {
			String percorsoCompleto = appDirectoryPath+ nomeRistorante + ".txt";
			ConfiguratoreRistorante configuratoreRistorante = new ConfiguratoreRistorante();
			configuratoreRistorante.caricaIstanzaOggettoDaFile(percorsoCompleto);
			ristoranteTrovato = Ristorante.getInstance(nomeRistorante);
			System.out.println("Benvenuto! Ristorante: " + ristoranteTrovato.getNome());
		} 
		return ristoranteTrovato;
	}
}
