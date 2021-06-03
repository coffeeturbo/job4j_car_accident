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

    private final HashMap<Integer, Rule> rules = new HashMap<>(Map.of(
            1, Rule.builder().id(1).name("Статья. 1").build(),
            2, Rule.builder().id(2).name("Статья. 2").build(),
            3, Rule.builder().id(3).name("Статья. 3").build()
    ));

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

    public AccidentMem() {
        this.ai = new AtomicInteger(accidents.size() + 1);
    }

    // Accident
    @Override
    public List<Accident> getAll() {
        return new ArrayList<>(accidents.values());
    }

    @Override
    public Accident create(Accident accident) {
        accident.setId(ai.get());
        accidents.put(accident.getId(), accident);
        return accident;
    }

    @Override
    public Accident update(Accident accident) {
        accidents.put(accident.getId(), accident);
        return accident;
    }

    @Override
    public Accident findAccidentById(int id) {
        return accidents.get(id);
    }

    // AccidentType
    @Override
    public List<AccidentType> getAccidentTypes() {
        return  new ArrayList<>(types.values());
    }

    @Override
    public AccidentType findTypeById(int id) {
        return types.get(id);
    }

    // Rule
    @Override
    public Rule findRuleById(int id) {
        return rules.get(id);
    }

    @Override
    public List<Rule> getRules() {
        return new ArrayList<>(rules.values());
    }
}
