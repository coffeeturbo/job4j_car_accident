package accident.repository;

import accident.model.Rule;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RuleMem {
    private  static List<Rule> rules = List.of(
            Rule.builder().id(1).name("Статья. 1").build(),
            Rule.builder().id(2).name("Статья. 2").build(),
            Rule.builder().id(3).name("Статья. 3").build()
    );

    public static List<Rule> getRules() {
        return rules;
    }
}
