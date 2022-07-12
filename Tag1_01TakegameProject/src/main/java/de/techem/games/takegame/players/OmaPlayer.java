package de.techem.games.takegame.players;

import java.util.Random;

public class OmaPlayer extends AbstractTakeGamePlayer {

	private Random random = new Random();
	@Override
	public Integer doTurn(Integer scene) {
		
		return random.nextInt(4) + 1;
	}

}
