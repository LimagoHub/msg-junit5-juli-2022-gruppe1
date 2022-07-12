package de.app;

import de.techem.clients.GameClient;
import de.techem.games.takegame.TakeGameImpl;
import de.techem.games.takegame.players.ComputerPlayer;
import de.techem.games.takegame.players.HumanPlayer;
import de.techem.games.takegame.players.OmaPlayer;
import de.techem.io.ConsolenWriter;

public class Main {

	public static void main(String[] args) {
		TakeGameImpl impl = new TakeGameImpl(new ConsolenWriter());
		impl.addPlayer(new HumanPlayer());
		impl.addPlayer(new ComputerPlayer());
		GameClient client = new GameClient(impl);
		client.run();
	}

}
