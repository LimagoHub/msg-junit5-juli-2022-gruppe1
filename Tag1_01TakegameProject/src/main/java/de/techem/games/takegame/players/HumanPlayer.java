package de.techem.games.takegame.players;

import java.util.Scanner;

public class HumanPlayer extends AbstractTakeGamePlayer {
	private static final String USER_PROMPT = "Es gibt %s Steine. Bitte nehmen Sie 1, 2 oder 3.";
	private Scanner scanner = new Scanner(System.in);

	@Override
	public Integer doTurn(Integer stones) {
		System.out.println(String.format(USER_PROMPT, stones));
		return scanner.nextInt();
	}

}
