package com.jhonny.reactive.demoreactivomitocode.util;

import com.jhonny.reactive.demoreactivomitocode.model.Persona;
import com.jhonny.reactive.demoreactivomitocode.model.PersonaDTO;
import org.springframework.stereotype.Component;

@Component
public class ConverterPersona {

    public Persona convertirPersonaDtoAPersona(PersonaDTO personaDTO){
        return Persona.builder()
                .idPersona(personaDTO.getIdPersona())
                .nombre(personaDTO.getNombre())
                .build();
    }
}
