package persistence;
/**
 * Implements persistence for the whole Chirp system
 * @author jerry
 *
 */

import model.ChirpSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import application.ChirpApplication;

public class ChirpPersistence {
	private static String filename = "data.chirp";
		public static void setFilename(String filename) {
		  ChirpPersistence.filename = filename;
		}

		public static void save() {
		  save(ChirpApplication.getChirpSystem());
		}

		public static void save(ChirpSystem system) {
			try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(new File(filename)));
		 	os.writeObject(system);
			} catch (IOException e) {
				
			}
		}

		public static ChirpSystem load() {
			ChirpSystem system = null;
			try { 
			ObjectInputStream oi = new ObjectInputStream(new FileInputStream(new File(filename)));
		   system = (ChirpSystem) oi.readObject();
			} catch (IOException | ClassNotFoundException e) {
				
			}
		  // model cannot be loaded - create empty system
		  if (system == null) {
		    system = new ChirpSystem();
		  }
		  return system;
		}
}
