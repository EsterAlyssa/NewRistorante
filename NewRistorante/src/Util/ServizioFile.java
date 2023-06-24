package Util;
import java.io.*;


public class ServizioFile
{
	public static final String MSG_SUCCESSO = "File creato con successo ";
	public static final String MSG_ERR_CREAZIONE = "ATTENZIONE! Si è verificato un errore durante la creazione del file ";

	public static File creaFile(String nomeFile) {
		File file = new File(nomeFile);

		try {
			if (file.createNewFile()) {
				System.out.println(MSG_SUCCESSO);
			}
			file.setWritable(true, false);
			file.setReadable(true, false);
		} catch (IOException e) {
			System.out.println(MSG_ERR_CREAZIONE);
		}

		return file;
	}

	public static boolean ePrimaApertura(String directoryPath) {
		File ristoranteDirectory = new File(directoryPath);

		if (!ristoranteDirectory.exists()) {
			if (ristoranteDirectory.mkdir()) {
				System.out.println("Directory creata con successo.");
				return true; // Restituisci true se la directory è stata creata
			} else {
				System.out.println("Impossibile creare la directory.");
				return false; // Restituisci false se la directory non può essere creata
			}
		}

		return false; // Restituisci false se la directory esiste
	}

	public static String getNomeFileSenzaEstensione(String filePath) {
		File file = new File(filePath);
		String fileName = file.getName();
		int dotIndex = fileName.lastIndexOf('.');
		if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
			return fileName.substring(0, dotIndex);
		}
		return fileName;
	}

	public static String getNomeFile(String DIRECTORY_PATH) {
		File directory = new File(DIRECTORY_PATH);
		if (directory.exists() && directory.isDirectory()) {
			File[] files = directory.listFiles();
			if (files != null && files.length > 0) {
				String fileName = files[0].getName();
				return ServizioFile.getNomeFileSenzaEstensione(fileName);
			}
		}
		return null; // Restituisci null se non è stato possibile trovare il file
	}
	
}

