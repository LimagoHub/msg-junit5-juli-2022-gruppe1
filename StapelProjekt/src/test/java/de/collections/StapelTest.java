package de.collections;

import mockit.MockUp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StapelTest {

    @InjectMocks private Stapel objectUnderTest;


    @Nested
    @DisplayName("Tests für isEmpty")
    class EmptyMethodTests {
        @Test
        @DisplayName("Gegeben ist ein leerer Stapel. Wenn isEmpty aufgerufen wird sollte die Methode true zurück geben")
        void isEmpty_emptyStack_returnsTrue() {
            assertThat(objectUnderTest.isEmpty()).isTrue();
        }

        @Test
        @DisplayName("Gegeben ist ein nicht leerer Stapel. Wenn isEmpty aufgerufen wird sollte die Methode false zurück geben")
        void isEmpty_NotemptyStack_returnsFalse() throws StapelException {
            objectUnderTest.push(0);
            assertThat(objectUnderTest.isEmpty()).isFalse();
        }
    }
    @Nested
    class FullMethodTests {
        @Test
        @DisplayName("Gegeben ist ein voller Stapel. Wenn isFull aufgerufen wird sollte die Methode true zurück geben")
        void isIsfull_vollerStack_returnsTrue() throws StapelException {
            // Arrange



            for (int i = 0; i < 10; i++)
                objectUnderTest.push(0);

            // Act
            boolean result = objectUnderTest.isFull();

            // Assertion
            assertThat(result).isTrue();
        }

    }

    @Nested
    class PushMethodTests {
        @Test
        @DisplayName("Gegeben ist ein voller Stapel. Wenn isFull aufgerufen wird sollte die Methode true zurück geben")
        void push_overflow_throwsStapelException_and_message () throws StapelException{
            // Arrange



            for (int i = 0; i < 10; i++)
                objectUnderTest.push(0);

            // Act + Assertion
            StapelException ex = assertThrows(StapelException.class , ()->objectUnderTest.push(0));

            assertThat("Overflow").isEqualTo(ex.getMessage());


        }

        @Test
        @DisplayName("Gegeben ist ein voller Stapel. Wenn isFull aufgerufen wird sollte die Methode true zurück geben")
        void push_fillUpToLimit_throwsStapelException_and_message () throws StapelException{
            // Arrange



            for (int i = 0; i < 9; i++)
                objectUnderTest.push(0);


            assertDoesNotThrow(()->objectUnderTest.push(0));

        }

    }

    @Test
    void sinnlos() {

        new MockUp<UUID>() {
            @mockit.Mock
            public String toString() {
                return "1234";
            }
        };
        String s = UUID.randomUUID().toString();
        assertThat(s).isEqualTo("1234");


        new MockUp<Bar>() {
            @mockit.Mock
            public long currentTimeMillis() {
                return 1000;
            }
        };

        assertThat(Bar.currentTimeMillis()).isEqualTo(1000);
    }


    static class Bar {
        public static long currentTimeMillis() {
            return 5;
        }
    }

}