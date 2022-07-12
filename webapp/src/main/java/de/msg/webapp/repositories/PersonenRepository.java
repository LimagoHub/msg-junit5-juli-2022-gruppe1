package de.msg.webapp.repositories;

import de.msg.webapp.repositories.entities.PersonEntity;
import org.springframework.data.repository.CrudRepository;

public interface PersonenRepository extends CrudRepository<PersonEntity, String> , PersonCustomRepo{

    Iterable<PersonEntity> findByVorname(String vorname);
}
