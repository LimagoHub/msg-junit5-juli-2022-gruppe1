package de.msg.game.takegame.player;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.ThrowingConsumer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import de.msg.io.Writer;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ComputerPlayerTest {

    @InjectMocks
    private ComputerPlayer objectUnderTest;

    @Mock
    private Writer writer;


    @Test
    void doTurn_divisionsRest1_returns1() {
        assertEquals(1, objectUnderTest.doTurn(21));
        verify(writer,times(1)).write("Computer nimmt 1 Steine.");
    }

    @Test
    void doTurn_divisionsRest2_returns1() {
        assertEquals(1, objectUnderTest.doTurn(22));
        verify(writer,times(1)).write("Computer nimmt 1 Steine.");
    }

    @Test
    void doTurn_divisionsRest3_returns2() {
        assertEquals(2, objectUnderTest.doTurn(23));
        verify(writer,times(1)).write("Computer nimmt 2 Steine.");
    }

    static class Param {
        private final int input;
        private final int excpected;

        public Param(int input, int excpected) {
            this.input = input;
            this.excpected = excpected;
        }

        public int getInput() {
            return input;
        }

        public int getExcpected() {
            return excpected;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Param{");
            sb.append("input=").append(input);
            sb.append(", excpected=").append(excpected);
            sb.append('}');
            return sb.toString();
        }
    }
}
