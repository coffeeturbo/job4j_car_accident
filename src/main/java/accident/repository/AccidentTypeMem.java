package accident.repository;

import accident.model.Accident;
import accident.model.AccidentType;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class AccidentTypeMem {

    private static final List<AccidentType> types = List.of(
            AccidentType.builder().id(1).name("Две машины").build(),
            AccidentType.builder().id(2).name("Машина и человек").build(),
            AccidentType.builder().id(3).name("Машина и велосипед").build()
    );

    public static List<AccidentType> getAccidentTypes() {
        return types;
    }
}
