package model;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
public class Persona {
    private Integer idPersona;
    private String nombre;
}