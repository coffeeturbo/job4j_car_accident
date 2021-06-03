package accident.control;

import accident.model.Accident;
import accident.model.AccidentType;
import accident.model.Rule;
import accident.repository.AccidentJdbcTemplate;
import accident.repository.Repository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AccidentControl {
    private final Repository accidents;

    public AccidentControl(AccidentJdbcTemplate accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", accidents.getAccidentTypes());
        model.addAttribute("rules", accidents.getRules());
        return "accident/create";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("types", accidents.getAccidentTypes());
        model.addAttribute("rules", accidents.getRules());
        model.addAttribute("accident", accidents.findAccidentById(id));
        return "accident/update";
    }

    @PostMapping("/save")
    public String save(
            @ModelAttribute Accident accident,
            @RequestParam("type.id") Integer typeId,
            @RequestParam("rIds") Integer[] rIds
    ) {
        List<Rule> rules = Arrays.stream(rIds)
                .map(accidents::findRuleById)
                .collect(Collectors.toList());

        AccidentType type = accidents.findTypeById(typeId);

        accident.setType(type);
        accident.setRules(rules);
        accidents.save(accident);
        return "redirect:/";
    }
}
