package accident.repository;

import accident.model.Accident;
import accident.model.AccidentType;
import accident.model.Rule;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem implements accident.repository.Repository {
    private final AtomicInteger ai;



    private final HashMap<Integer, AccidentType> types = new HashMap<>(Map.of(
            1, AccidentType.builder().id(1).name("Две машины").build(),
            2, AccidentType.builder().id(2).name("Машина и человек").build(),
            3, AccidentType.builder().id(3).name("Машина и велосипед").build()
    ));

    private List<Rule> rules = List.of(
            Rule.builder().id(1).name("Статья. 1").build(),
            Rule.builder().id(2).name("Статья. 2").build(),
            Rule.builder().id(3).name("Статья. 3").build()
    );

    private final HashMap<Integer, Accident> accidents = new HashMap<>(
            Map.of(
                    1, Accident.builder()
                            .id(1)
                            .name("Происшествие 1")
                            .address("Japan")
                            .text("Овария страшная")
                            .type(getAccidentTypes().get(0))
                            .rules(getRules())
                            .build(),
                    2, Accident.builder()
                            .id(2)
                            .name("Происшествие 2")
                            .address("USA")
                            .text("Овария не страшная")
                            .type(getAccidentTypes().get(1))
                            .build(),
                    3, Accident.builder()
                            .id(3)
                            .name("Происшествие 3")
                            .address("Россия")
                            .text("Пьянство за рулем")
                            .type(getAccidentTypes().get(2))
                            .build()
            )
    );


    @Override
    public List<Accident> getAll() {
        return new ArrayList<>(accidents.values());
    }

    public List<Rule> getRules() {
        return rules;
    }

    public List<AccidentType> getAccidentTypes() {
        return  new ArrayList<>(types.values());
    }

    public AccidentMem() {
        this.ai = new AtomicInteger(accidents.size() + 1);
    }

    public Collection<Accident> getAccidents() {
        return accidents.values();
    }

    public Accident save(Accident accident) {
        return accident.getId() == 0 ? create(accident) : update(accident);
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
