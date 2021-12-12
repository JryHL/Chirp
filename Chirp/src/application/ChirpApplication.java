package application;

import model.ChirpSystem;
import persistence.ChirpPersistence;

public class ChirpApplication {
	private ChirpSystem chirpSystem;
	private static ChirpApplication app;
	public static void main(String[] args) {
		app = new ChirpApplication();
		app.chirpSystem = ChirpPersistence.load();
	}
	
	public static ChirpSystem getChirpSystem() {
		return app.chirpSystem;
	}
}
