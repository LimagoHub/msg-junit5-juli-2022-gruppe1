package de.techem.games.takegame.players;

import de.techem.io.ConsolenWriter;
import de.techem.io.Writer;

public class ComputerPlayer extends AbstractTakeGamePlayer{

	private static final int zuege[] = {3,1,1,2};
	
	private Writer writer;
	
	public ComputerPlayer() {
		this(new ConsolenWriter());
		
	}
	public ComputerPlayer(Writer writer) {
		super();
		this.writer = writer;
	}


	@Override
	public Integer doTurn(Integer stones) {
		
		int turn = zuege[stones % 4];
		writer.write(String.format("Computer nimmt %s Steine.", turn));
		return turn;
	}

}
