package accident.repository;

import accident.model.Accident;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class AccidentMem {

    private final HashMap<Integer, Accident> accidents = new HashMap<>(
            Map.of(
                    1, Accident.builder()
                            .id(1)
                            .name("Происшествие 1")
                            .address("Japan")
                            .text("Овария страшная")
                            .build(),
                    2, Accident.builder()
                            .id(2)
                            .name("Происшествие 2")
                            .address("USA")
                            .text("Овария не страшная")
                            .build(),
                    3, Accident.builder()
                            .id(3)
                            .name("Происшествие 3")
                            .address("Россия")
                            .text("Пьянство за рулем")
                            .build()
            )
    );

    public Collection<Accident> getAccidents() {
        return accidents.values();
    }

    public Accident create(Accident accident) {
        Random rm = new Random();
        accident.setId(Math.abs(rm.nextInt()));
        accidents.put(accident.getId(), accident);
        return accident;
    }
}
