package accident.control;

import accident.model.Accident;
import accident.model.AccidentType;
import accident.model.Rule;
import accident.repository.AccidentMem;
import accident.repository.AccidentTypeMem;
import accident.repository.RuleMem;
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
    private final AccidentMem accidents;

    public AccidentControl(AccidentMem accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", AccidentTypeMem.getAccidentTypes());
        model.addAttribute("rules", RuleMem.getRules());
        return "accident/create";
    }
    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {

        model.addAttribute("rules", RuleMem.getRules());
        model.addAttribute("accident", accidents.findById(id));
        return "accident/update";
    }

    @PostMapping("/save")
    public String save(
            @ModelAttribute Accident accident,
            @RequestParam("type.id") Integer typeId,
            @RequestParam("rIds") Integer[] rIds
    ) {
        List<Rule> rules = Arrays.stream(rIds)
                .map(integer -> RuleMem.getRules().get(integer))
                .collect(Collectors.toList());

        AccidentType type = AccidentTypeMem.getAccidentTypes().get(typeId);

        accident.setType(type);
        accident.setRules(rules);
        accidents.save(accident);
        return "redirect:/";
    }
}
