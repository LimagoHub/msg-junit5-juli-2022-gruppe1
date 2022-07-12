package de.msg.webapp.services.inner;

import de.msg.webapp.repositories.PersonenRepository;
import de.msg.webapp.services.PersonenService;
import de.msg.webapp.services.PersonenServiceException;
import de.msg.webapp.services.mapper.PersonMapper;
import de.msg.webapp.services.models.Person;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.validation.Validator;
import java.util.List;
import java.util.Optional;


@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = PersonenServiceException.class)

public class PersonenServiceImpl implements PersonenService {

    private final PersonenRepository repo;
    private final PersonMapper mapper;

    private final List<String> blackList;

    public PersonenServiceImpl(PersonenRepository repo, PersonMapper mapper, @Qualifier("blackList") List<String> blackList) {
        this.repo = repo;
        this.mapper = mapper;
        this.blackList = blackList;
    }
    /*
        Validierung
        person == null -> PSE
        vorname == null ->PSE
        vorname < 2 > PSE
        nachname == null ->PSE
        nachname < 2 > PSE

        Fachliche Prüfung
        Attila -> PSE

        Unerwarteten Exceptions -> PSE

        Update -> true
        Insert -> false

     */

    @Override
    public boolean speichern(Person person) throws PersonenServiceException {

        try {
            return speichernImpl(person);
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Fehler beim Speichern", e);
        }
    }

    private boolean speichernImpl(Person person) throws PersonenServiceException {
        checkPerson(person);

        boolean result = repo.existsById(person.getId());

        repo.save(mapper.convert(person));

        return result;
    }

    private void checkPerson(Person person) throws PersonenServiceException {
        validatePerson(person);
        businessCheck(person);
    }

    private void businessCheck(Person person) throws PersonenServiceException {
        if(blackList.contains(person.getVorname()))
            throw new PersonenServiceException("Antipath");
    }

    private void validatePerson(Person person) throws PersonenServiceException {
        if(person == null)
            throw new PersonenServiceException("Person darf nicht null sein.");


        if(person.getVorname() == null || person.getVorname().length() < 2)
            throw new PersonenServiceException("Vorname ist zu kurz.");

        if(person.getNachname() == null || person.getNachname().length() < 2)
            throw new PersonenServiceException("Nachname ist zu kurz.");
    }

    @Override
    public boolean loeschen(String id) throws PersonenServiceException {
        try {
            if(repo.existsById(id)) {
                repo.deleteById(id);
                return true;
            }
            return false;
        } catch (RuntimeException e) {
            throw new PersonenServiceException(e);
        }
    }

    @Override
    public Optional<Person> findById(String id) throws PersonenServiceException {
        try {
           return repo.findById(id).map(mapper::convert);
        } catch (RuntimeException e) {
            throw new PersonenServiceException(e);
        }
    }

    @Override
    public Iterable<Person> findAll() throws PersonenServiceException {
        try {
           return mapper.convert(repo.findAll());

        } catch (RuntimeException e) {
            throw new PersonenServiceException(e);
        }
    }
}
