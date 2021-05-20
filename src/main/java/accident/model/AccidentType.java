package accident.model;

import lombok.*;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class AccidentType {
    private Integer id;
    private String name;
}
