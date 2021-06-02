package accident.repository;

import accident.model.Accident;

import java.util.List;

public interface Repository {

    Accident save(Accident accident);

    List<Accident> getAll();
}
