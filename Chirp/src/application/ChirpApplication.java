package application;

import model.ChirpSystem;
import persistence.ChirpPersistence;

public class ChirpApplication {
	private  static ChirpSystem chirpSystem;
	public static void main(String[] args) {
		chirpSystem = ChirpPersistence.load();
	}
	
	public static ChirpSystem getChirpSystem() {
		return chirpSystem;
	}
}
