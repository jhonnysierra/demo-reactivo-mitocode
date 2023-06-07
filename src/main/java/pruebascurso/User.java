package pruebascurso;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
public class User {
    private String firstName;
    private String lastname;
    private String job;
}
