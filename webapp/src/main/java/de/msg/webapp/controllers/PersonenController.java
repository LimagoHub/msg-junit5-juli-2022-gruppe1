package de.msg.webapp.controllers;


import de.msg.webapp.controllers.dtos.PersonDto;
import de.msg.webapp.controllers.mapper.PersonDtoMapper;
import de.msg.webapp.services.PersonenService;
import de.msg.webapp.services.PersonenServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping(path="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PersonDto> findPersonById(@PathVariable  String id) throws PersonenServiceException {

        return ResponseEntity.of( service.findById(id).map(mapper::convert));
    }

    @GetMapping(path="", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Iterable<PersonDto>> findeAlle() throws PersonenServiceException {

        return ResponseEntity.ok( mapper.convert(service.findAll()));
    }


}
