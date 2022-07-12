package de.msg.webapp.controllers.mapper;


import de.msg.webapp.controllers.dtos.PersonDto;
import de.msg.webapp.repositories.entities.PersonEntity;
import de.msg.webapp.services.models.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonDtoMapper {

    Person convert(PersonDto dto);
    PersonDto convert(Person person);
    Iterable<PersonDto> convert(Iterable<Person> person);
}
