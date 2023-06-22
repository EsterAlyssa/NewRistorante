package Util;
import java.io.*;


public class ServizioFile
{
	private final static String MSG_NO_FILE = "ATTENZIONE! Non trovo il file ";
	private final static String MSG_NO_LETTURA = "ATTENZIONE! Problemi con la lettura del file ";
	private final static String MSG_NO_SCRITTURA = "ATTENZIONE! Problemi con la scrittura del file ";
	private final static String MSG_NO_CHIUSURA ="ATTENZIONE! Problemi con la chiusura del file ";
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

	public static boolean ePrimaApertura(String RISTORANTE_DIRECTORY_PATH) {
		File ristoranteDirectory = new File(RISTORANTE_DIRECTORY_PATH);

		if (!ristoranteDirectory.exists()) {
			if (ristoranteDirectory.mkdir()) {
				System.out.println("Directory 'FileRistorante' creata con successo.");
				return true; // Restituisci true se la directory è stata creata
			} else {
				System.out.println("Impossibile creare la directory 'FileRistorante'.");
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
	
	public static Object caricaSingoloOggetto (File f){
		Object letto = null;
		ObjectInputStream ingresso = null;

		try{
			ingresso = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)));
			letto = ingresso.readObject();
		} 
		catch (FileNotFoundException excNotFound){
			System.out.println(MSG_NO_FILE);
		}
		catch (IOException excLettura){
			System.out.println(MSG_NO_LETTURA );
		}
		catch (ClassNotFoundException excLettura){
			System.out.println(MSG_NO_LETTURA );
		}
		finally{
			if (ingresso != null)
			{
				try{
					ingresso.close();
				}
				catch (IOException excChiusura){
					System.out.println(MSG_NO_CHIUSURA);
				}
			}
		} // finally
		return letto;
	} // metodo caricaSingoloOggetto

	public static void salvaSingoloOggetto (File f, Object daSalvare){
		ObjectOutputStream uscita = null;
		try{
			uscita = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
			uscita.writeObject(daSalvare);
		}
		catch (IOException excScrittura){
			System.out.println(MSG_NO_SCRITTURA);
		}
		finally{
			if (uscita != null){
				try{
					uscita.close();
				}
				catch (IOException excChiusura){
					System.out.println(MSG_NO_CHIUSURA);
				}
			}
		} // finally
	} // metodo salvaSingoloOggetto
	
	
	public static void saveObjectToFile(Object object, String filePath) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(object);
            System.out.println("Oggetto salvato con successo su: " + filePath);
        } catch (IOException e) {
            System.out.println("Si è verificato un errore durante il salvataggio dell'oggetto su: " + filePath);
            e.printStackTrace();
        }
    }
}

