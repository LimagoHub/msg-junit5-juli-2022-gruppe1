package de.msg.webapp.controllers;


import de.msg.webapp.controllers.dtos.PersonDto;
import de.msg.webapp.controllers.mapper.PersonDtoMapper;
import de.msg.webapp.services.PersonenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/personen")
@RequiredArgsConstructor
public class PersonenController {

    private final PersonenService service;
    private final PersonDtoMapper mapper;

    public ResponseEntity<PersonDto> findPersonById(String id) {
        return ResponseEntity.ok( new PersonDto());
    }

    public ResponseEntity<Iterable<PersonDto>> findAll(String id) {
        List<PersonDto> liste = Collections.emptyList();
        return ResponseEntity.ok(liste );
    }
}
