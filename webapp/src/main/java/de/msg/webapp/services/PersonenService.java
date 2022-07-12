package de.msg.webapp.services;

import de.msg.webapp.services.models.Person;

import java.util.Optional;

public interface PersonenService {

    public boolean speichern(Person person) throws PersonenServiceException ;
    default boolean loeschen(Person person) throws PersonenServiceException{
        return loeschen(person.getId());
    }
    public boolean loeschen(String id) throws PersonenServiceException ;
    public Optional<Person> findById(String id) throws PersonenServiceException ;
    public Iterable<Person> findAll() throws PersonenServiceException ;

}
