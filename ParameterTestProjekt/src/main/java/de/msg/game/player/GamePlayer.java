package de.msg.game.player;

public interface GamePlayer<SCENE, TURN> {
	String getName();
	TURN doTurn(final SCENE scene);
}
