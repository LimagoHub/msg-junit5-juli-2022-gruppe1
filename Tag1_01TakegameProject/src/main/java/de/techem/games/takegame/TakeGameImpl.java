package de.techem.games.takegame;

import de.techem.games.AbstractGame;
import de.techem.io.Writer;

public class TakeGameImpl extends AbstractGame<Integer, Integer> {
	
	
	public TakeGameImpl(final Writer writer) {
		super(writer);
		scene = 23;
		
	}
	

	
	protected boolean isTurnValid() {
		return turn >= 1 && turn <= 3;
	}

	protected void updateScene() {
		scene -= turn;
	}
	
	protected boolean isGameOver() {
		return scene <= 0 || getPlayers().isEmpty();
	}
	
	

}
