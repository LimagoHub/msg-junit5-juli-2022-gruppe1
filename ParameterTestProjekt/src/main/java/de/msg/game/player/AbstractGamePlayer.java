package de.msg.game.player;

public abstract class AbstractGamePlayer<SCENE, TURN> implements GamePlayer<SCENE, TURN> {

	
		
		private String name= getClass().getSimpleName();
		
		public AbstractGamePlayer() {
			
		}

		public AbstractGamePlayer(String name) {
			
			this.name = name;
		}

		@Override
		public String getName() {
			
			return name;
		}

		
	

}
