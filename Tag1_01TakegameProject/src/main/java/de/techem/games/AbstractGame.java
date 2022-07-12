package de.techem.games;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.techem.games.players.GamePlayer;
import de.techem.io.Writer;

public abstract class AbstractGame<SCENE, TURN> implements Game {

	private static final String LOSING_MESSAGE = "%s hat verloren";
	private static final String ERROR_MESSAGE = "Ungueltiger Zug";

	protected GamePlayer<SCENE, TURN> currentPlayer = null;
	protected SCENE scene;
	protected TURN turn;

	private List<GamePlayer<SCENE, TURN>> players = new ArrayList<>();

	private final Writer writer;

	public AbstractGame(final Writer writer) {
		this.writer = writer;
	}

	protected List<GamePlayer<SCENE, TURN>> getPlayers() {
		return Collections.unmodifiableList(players);
	}

	public void addPlayer(GamePlayer<SCENE, TURN> player) {
		players.add(player);
	}

	public void removePlayer(GamePlayer<SCENE, TURN> player) {
		players.remove(player);
	}

	@Override
	public final void play() {
		while (!isGameOver())
			executeTurns();

	}

	private void executeTurns() { // operation
		for (GamePlayer<SCENE, TURN> player : players)
			prepareTurn(player);
	}

	private void prepareTurn(GamePlayer<SCENE, TURN> player) {
		currentPlayer = player;
		executeSingleTurn();
	}

	private void executeSingleTurn() { // Integration
		if (initTurnFailed())
			return;

		//int n = 5;
		// TODO Rename
		invokePlayersTurn();
		terminateTurn();
	}

	private boolean initTurnFailed() {
		prepare();
		return isGameOver();
	}

	private void invokePlayersTurn() {
		do
			turn = currentPlayer.doTurn(scene);
		while (playersTurnIsInvalid());
	}
	
	private void terminateTurn() {// Integration
		updateScene();
		printGameOverMessage();
	}
	
	private boolean playersTurnIsInvalid() {
		if (isTurnValid()) {
			return false;
		}
		print(ERROR_MESSAGE);
		return true;
	}

	

	private void printGameOverMessage() {// Operation
		if (isGameOver())
			print(String.format(LOSING_MESSAGE, currentPlayer.getName()));

	}

	protected void print(String message) {
		writer.write(message);
	}

	protected abstract boolean isGameOver();

	protected abstract boolean isTurnValid();

	protected abstract void updateScene();

	protected void prepare() {
		// Ok
	}

}
