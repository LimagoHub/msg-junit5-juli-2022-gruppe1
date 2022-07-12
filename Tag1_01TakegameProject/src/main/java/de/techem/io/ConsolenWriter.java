package de.techem.io;

public class ConsolenWriter implements Writer {

	@Override
	public void write(String message) {
		try {
			Thread.sleep(1000);
			System.out.println(message);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
