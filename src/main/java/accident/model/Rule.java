package accident.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Rule {
    private Integer id;
    private String name;
}
