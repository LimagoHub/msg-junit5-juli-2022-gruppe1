package de.msg.webapp.services.mapper;


import de.msg.webapp.repositories.entities.PersonEntity;
import de.msg.webapp.services.models.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface PersonMapper {

    Person convert(PersonEntity entity);
    PersonEntity convert(Person person);
    Iterable<Person> convert(Iterable<PersonEntity> entities);
}
