package de.msg.webapp.controllers;

import de.msg.webapp.controllers.dtos.PersonDto;
import de.msg.webapp.services.PersonenService;
import de.msg.webapp.services.PersonenServiceException;
import de.msg.webapp.services.models.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Sql({"/create.sql", "/insert.sql"})
@ExtendWith(SpringExtension.class)
class PersonenControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private PersonenService personenServiceMock;

    @Test
    void test1() throws PersonenServiceException {

        Optional<Person> optionalPerson = Optional.of(Person.builder().id("3").vorname("Max").nachname("Mustermann").build());

        Mockito.when(personenServiceMock.findById(Mockito.anyString())).thenReturn(optionalPerson);

        PersonDto dto = restTemplate.getForObject("/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", PersonDto.class);

        assertEquals("3", dto.getId());
        assertEquals("Max", dto.getVorname());

        Mockito.verify(personenServiceMock, Mockito.times(1)).findById("b2e24e74-8686-43ea-baff-d9396b4202e0");
    }

    @Test
    void test2() throws PersonenServiceException {

        Optional<Person> optionalPerson = Optional.of(Person.builder().id("3").vorname("Max").nachname("Mustermann").build());

        Mockito.when(personenServiceMock.findById(Mockito.anyString())).thenReturn(optionalPerson);

        String string = restTemplate.getForObject("/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", String.class);

        System.out.println(string);
    }

    @Test
    void test3() throws PersonenServiceException {

        Optional<Person> optionalPerson = Optional.of(Person.builder().id("3").vorname("Max").nachname("Mustermann").build());

        Mockito.when(personenServiceMock.findById(Mockito.anyString())).thenReturn(optionalPerson);

        ResponseEntity<PersonDto> entity = restTemplate.getForEntity("/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", PersonDto.class);

        PersonDto dto = entity.getBody();

        assertEquals("3", dto.getId());
        assertEquals("Max", dto.getVorname());
        assertEquals(HttpStatus.OK,entity.getStatusCode());
        Mockito.verify(personenServiceMock, Mockito.times(1)).findById("b2e24e74-8686-43ea-baff-d9396b4202e0");
    }

    @Test
    void test4() throws PersonenServiceException {

        Optional<Person> optionalPerson = Optional.empty();

        Mockito.when(personenServiceMock.findById(Mockito.anyString())).thenReturn(optionalPerson);

        ResponseEntity<PersonDto> entity = restTemplate.getForEntity("/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", PersonDto.class);




        assertEquals(HttpStatus.NOT_FOUND,entity.getStatusCode());
        Mockito.verify(personenServiceMock, Mockito.times(1)).findById("b2e24e74-8686-43ea-baff-d9396b4202e0");
    }

    @Test
    void test5() throws PersonenServiceException {

        Optional<Person> optionalPerson = Optional.empty();

        Mockito.when(personenServiceMock.findById(Mockito.anyString())).thenReturn(optionalPerson);

        ResponseEntity<PersonDto> entity = restTemplate.exchange("/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", HttpMethod.GET, null, PersonDto.class);




        assertEquals(HttpStatus.NOT_FOUND,entity.getStatusCode());
        Mockito.verify(personenServiceMock, Mockito.times(1)).findById("b2e24e74-8686-43ea-baff-d9396b4202e0");
    }

    @Test
    void test6() throws PersonenServiceException {

        List<Person> liste = List.of(
                Person.builder().id("3").vorname("Max").nachname("Mustermann").build(),
                Person.builder().id("4").vorname("Erika").nachname("Mustermann").build());


        //HttpEntity<PersonDto> httpEntity = new HttpEntity<>(PersonDto.builder().id("3").vorname("Max").nachname("Mustermann").build());
        Mockito.when(personenServiceMock.findAll()).thenReturn(liste);

        ResponseEntity<List<PersonDto>> entity = restTemplate.exchange("/personen", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<PersonDto>>() {});




        assertEquals(HttpStatus.NOT_FOUND,entity.getStatusCode());
        Mockito.verify(personenServiceMock, Mockito.times(1)).findById("b2e24e74-8686-43ea-baff-d9396b4202e0");
    }
}