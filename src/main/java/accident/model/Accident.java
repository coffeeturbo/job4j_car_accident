package accident.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Accident {
    private Integer id;
    private String name;
    private String text;
    private String address;
    private AccidentType type;
    private List<Rule> rules;


}
