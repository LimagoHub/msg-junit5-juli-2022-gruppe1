package de.msg.webapp.services.inner;

import de.msg.webapp.repositories.PersonenRepository;
import de.msg.webapp.repositories.entities.PersonEntity;
import de.msg.webapp.services.PersonenService;
import de.msg.webapp.services.PersonenServiceException;
import de.msg.webapp.services.mapper.PersonMapper;
import de.msg.webapp.services.models.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonenServiceImplTest {

    @InjectMocks private PersonenServiceImpl objectUnderTest;

    @Mock private PersonenRepository repoMock;
    @Mock private PersonMapper mapperMock;
    @Mock private List<String> blacklistMock;

    @Nested
    @DisplayName("Tests für die Speichernmethoden")
    class SpeichernMethoden {

        @Test
        @DisplayName("null-Parameter sollte PersonenServiceException mit Meldung Bla, bla auslösen")
        void Nullparameter_throwsPersonenServiceException() throws PersonenServiceException {
            PersonenServiceException ex = assertThrows(PersonenServiceException.class, ()->objectUnderTest.speichern(null));
            assertEquals("Person darf nicht null sein.", ex.getMessage());
        }

        @Test
        @DisplayName("vorname ist null sollte PersonenServiceException mit Meldung Bla, bla auslösen")
        void vornamenull_throwsPersonenServiceException() throws PersonenServiceException {
            Person failPerson = Person.builder().id(UUID.randomUUID().toString()).vorname(null).nachname("Doe").build();

            PersonenServiceException ex = assertThrows(PersonenServiceException.class, ()->objectUnderTest.speichern(failPerson));
            assertEquals("Vorname ist zu kurz.", ex.getMessage());
        }

        @Test
        @DisplayName("vorname ist kürzer als sollte PersonenServiceException mit Meldung Bla, bla auslösen")
        void vornamezukurz_throwsPersonenServiceException() throws PersonenServiceException {
            Person failPerson = Person.builder().id(UUID.randomUUID().toString()).vorname("J").nachname("Doe").build();

            PersonenServiceException ex = assertThrows(PersonenServiceException.class, ()->objectUnderTest.speichern(failPerson));
            assertEquals("Vorname ist zu kurz.", ex.getMessage());
        }

        @Test
        @DisplayName("Person ist Antipath und sollte PersonenServiceException mit Meldung Bla, bla auslösen")
        void Antipath_throwsPersonenServiceException() throws PersonenServiceException {
            Person failPerson = Person.builder().id(UUID.randomUUID().toString()).vorname("John").nachname("Doe").build();

            // Recordmode
            when(blacklistMock.contains(anyString())).thenReturn(true);


            // Replay
            PersonenServiceException ex = assertThrows(PersonenServiceException.class, ()->objectUnderTest.speichern(failPerson));
            assertEquals("Antipath", ex.getMessage());

            verify(blacklistMock, times(1)).contains("John");
        }

        @Test
        @DisplayName("Person ist Antipath und sollte PersonenServiceException mit Meldung Bla, bla auslösen")
        void happyDay() throws PersonenServiceException {
            Person valid = Person.builder().id("1").vorname("John").nachname("Doe").build();


            // Recordmode
            when(blacklistMock.contains(anyString())).thenReturn(false);


            // Replay
            objectUnderTest.speichern(valid);
            InOrder inOrder = inOrder(blacklistMock, mapperMock);


            inOrder.verify(blacklistMock, times(1)).contains("John");
            inOrder.verify(mapperMock, times(1)).convert(valid);

        }
    }

}