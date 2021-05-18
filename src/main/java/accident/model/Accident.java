package accident.model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Accident {
    private Integer id;
    private String name;
    private String text;
    private String address;
}
