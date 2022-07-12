package de.techem.games;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.internal.stubbing.answers.DoesNothing;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import de.techem.games.players.GamePlayer;
import de.techem.io.Writer;
@ExtendWith(MockitoExtension.class)
class AbstractGameTest {
	
	private boolean gameover;
	private boolean isvalid;
	
	
	private AbstractGameDummyForTest objectUnderTest;
	private Writer writerMock;
	private GamePlayer<DummyScene, DummyTurn> playerMock;
	

	@BeforeEach
	public void init() {
		isvalid = true;
		gameover = false;
		playerMock = (GamePlayer<DummyScene, DummyTurn>) Mockito.mock(GamePlayer.class);
		writerMock = Mockito.mock(Writer.class);
		
		objectUnderTest = new AbstractGameDummyForTest(writerMock);
		objectUnderTest.addPlayer(playerMock);
		when(playerMock.getName()).thenReturn("MockPlayer");
	}
	

	@Test
	void play_GameOver_GameOverMessageIsPassedToWriter() {
		when(playerMock.doTurn(any(DummyScene.class))).thenAnswer(a->{
			gameover = true;
			return new DummyTurn();
		});
		objectUnderTest.play();
		verify(writerMock,times(1)).write("MockPlayer hat verloren");
	}
	
	@Test
	void play_UngueltigerZug_ErrorMessageMessageIsPassedToWriter() {
		isvalid = false;
		when(playerMock.doTurn(any(DummyScene.class))).thenAnswer(a->{
			gameover = true;
			return new DummyTurn();
		});

		//doThrow(Exception.class).when(playerMock).doTurn(any(DummyScene.class));
		//doNothing().when(writerMock).write(anyString());

		doAnswer((Answer<Void> ) a ->{isvalid = true; return null;}).when(writerMock).write(anyString());
		
		objectUnderTest.play();
		InOrder inOrder = inOrder(writerMock, playerMock);
		inOrder.verify(writerMock,times(1)).write("Ungueltiger Zug");
		inOrder.verify(writerMock,times(1)).write("MockPlayer hat verloren");
	}
	
	class AbstractGameDummyForTest extends AbstractGame<DummyScene, DummyTurn>{

		
		public AbstractGameDummyForTest(Writer writer) {
			super(writer);
			scene = new DummyScene();
		}

		@Override
		protected boolean isGameOver() {
			return gameover;
		}

		@Override
		protected boolean isTurnValid() {
			return isvalid;
		}

		@Override
		protected void updateScene() {
			// PAL (Problem anderer Leute
			
		}
		
	}

}

class DummyScene {
	
}

class DummyTurn {
	
}