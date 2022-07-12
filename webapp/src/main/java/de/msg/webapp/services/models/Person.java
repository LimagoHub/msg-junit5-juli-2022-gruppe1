package de.msg.webapp.services.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {


    @Setter(AccessLevel.PRIVATE)
    private String id;
    @Setter(AccessLevel.PRIVATE)
    private String vorname;
    @Setter(AccessLevel.PRIVATE)
    private String nachname;


}
