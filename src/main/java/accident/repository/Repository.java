package accident.repository;

import accident.model.Accident;
import accident.model.AccidentType;
import accident.model.Rule;

import java.util.List;

public interface Repository {

    // ACCIDENT
    List<Accident> getAll();
    Accident create(Accident accident);
    Accident update(Accident accident);

    default Accident save(Accident accident) {
        return accident.getId() != 0 ? update(accident) : create(accident);
    }

    Accident findAccidentById(int id);


    // ACCIDENTTYPE
    List<AccidentType> getAccidentTypes();
    AccidentType findTypeById(int id);

    // RULE
    List<Rule> getRules();
    Rule findRuleById(int id);
}
