package de.techem.clients;

import de.techem.games.Game;

public class GameClient {
	
	private final Game game;

	public GameClient(Game game) {
		this.game = game;
	}
	
	
	public void run() {
		game.play();
	}
}
