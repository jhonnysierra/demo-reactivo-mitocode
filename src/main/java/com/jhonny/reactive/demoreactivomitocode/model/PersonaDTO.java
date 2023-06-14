package com.jhonny.reactive.demoreactivomitocode.model;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
public class PersonaDTO {
    private Integer idPersona;
    private String nombre;
}