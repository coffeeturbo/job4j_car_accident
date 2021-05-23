package accident.repository;

import accident.model.Accident;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private final AtomicInteger ai;

    private final HashMap<Integer, Accident> accidents = new HashMap<>(
            Map.of(
                    1, Accident.builder()
                            .id(1)
                            .name("Происшествие 1")
                            .address("Japan")
                            .text("Овария страшная")
                            .type(AccidentTypeMem.getAccidentTypes().get(0))
                            .rules(RuleMem.getRules())
                            .build(),
                    2, Accident.builder()
                            .id(2)
                            .name("Происшествие 2")
                            .address("USA")
                            .text("Овария не страшная")
                            .type(AccidentTypeMem.getAccidentTypes().get(1))
                            .build(),
                    3, Accident.builder()
                            .id(3)
                            .name("Происшествие 3")
                            .address("Россия")
                            .text("Пьянство за рулем")
                            .type(AccidentTypeMem.getAccidentTypes().get(2))
                            .build()
            )
    );

    public AccidentMem() {
        this.ai = new AtomicInteger(accidents.size() + 1);
    }

    public Collection<Accident> getAccidents() {
        return accidents.values();
    }

    public Accident save(Accident accident) {
        return accident.getId()==null ? create(accident) : update(accident);
    }

    public Accident update(Accident accident) {
        accidents.put(accident.getId(), accident);
        return accident;
    }

    public Accident create(Accident accident) {
        accident.setId(ai.get());
        accidents.put(accident.getId(), accident);
        return accident;
    }

    public Accident findById(Integer id) {
        return accidents.get(id);
    }
}
