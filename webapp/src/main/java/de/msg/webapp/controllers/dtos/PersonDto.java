package de.msg.webapp.controllers.dtos;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDto {


    @NotNull
    @Size(min = 36, max = 36)
    private String id;

    @NotNull
    @Size(min = 2, max = 30)
    private String vorname;

    @NotNull
    @Size(min = 2, max = 30)
    private String nachname;


}
