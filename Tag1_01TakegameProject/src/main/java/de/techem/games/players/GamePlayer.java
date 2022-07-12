package de.techem.games.players;

public interface GamePlayer<SCENE, TURN> {
	String getName();
	TURN doTurn(final SCENE scene);
}
