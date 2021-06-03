package accident.repository;

import accident.model.Accident;
import accident.model.AccidentType;
import accident.model.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccidentMemTest {

    Repository repository;

    @BeforeEach
    protected void setUp() {
        repository = new AccidentMem();
    }

    @Test
    void getAllAccidentsSuccess() {
        List<Accident> accidents = repository.getAll();
        assertEquals(accidents.size(), 3);
    }

    @Test
    void getAllAccidentsFailed() {
        List<Accident> accidents = repository.getAll();
        assertNotEquals(accidents.size(), 200);
    }

    @Test
    void getAllAccidentTypesFailed() {
        List<AccidentType> types = repository.getAccidentTypes();
        assertNotEquals(types.size(), 9999);
    }

    @Test
    void getAllAccidentTypesSuccess() {
        List<AccidentType> types = repository.getAccidentTypes();
        assertEquals(types.size(), 3);
    }

    @Test
    void getAllRulesSuccess() {
        List<Rule> rules = repository.getRules();
        assertEquals(rules.size(), 3);
    }

    @Test
    void getAllRulesFailed() {
        List<Rule> rules = repository.getRules();
        assertNotEquals(rules.size(), 999);
    }

    @Test
    void whenFindTypeByIdSuccess() {
        AccidentType type = repository.findTypeById(1);
        assertEquals(type.getId(), 1);
        assertEquals(type.getName(), "Две машины");
    }

    @Test
    void whenFindTypeByIdFailed() {
        AccidentType type = repository.findTypeById(999);
        assertNull(type);
    }

    @Test
    void whenFindAccidentByIdSuccess() {
        Accident accident = repository.findAccidentById(1);
        assertEquals(accident.getId(), 1);
        assertEquals(accident.getName(), "Происшествие 1");
        assertEquals(accident.getAddress(), "Japan");
        assertEquals(accident.getText(), "Овария страшная");

        List<Rule> rules = accident.getRules();
        assertEquals(rules.size(), 3);
        assertEquals(rules.get(1).getName(), "Статья. 2");

        AccidentType type = accident.getType();
        assertEquals(type.getName(), "Две машины");
    }

    @Test
    void whenFindAccidentByIdFailed() {
        Accident accident = repository.findAccidentById(999);
        assertNull(accident);
    }
}