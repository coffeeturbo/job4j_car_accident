package accident.control;

import accident.model.Accident;
import accident.model.AccidentType;
import accident.model.Rule;
import accident.repository.*;
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
    private final AccidentRepository accidents;
    private final AccidentTypeRepository types;
    private final RuleRepository rules;

    public AccidentControl(AccidentRepository accidents,
                           AccidentTypeRepository types,
                           RuleRepository rules
    ) {
        this.accidents = accidents;
        this.types = types;
        this.rules = rules;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", types.findAll());
        model.addAttribute("rules", rules.findAll());
        return "accident/create";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("types", types.findAll());
        model.addAttribute("rules", rules.findAll());
        model.addAttribute("accident", accidents.findById(id).get());
        return "accident/update";
    }

    @PostMapping("/save")
    public String save(
            @ModelAttribute Accident accident,
            @RequestParam("type.id") Integer typeId,
            @RequestParam(value = "rIds", required = false) Integer[] rIds
    ) {
        List<Rule> rules = null;
        if (rIds != null) {
            rules = Arrays.stream(rIds)
                    .map(integer -> {
                        var rule = this.rules.findById(integer);
                        return rule.get();
                    })
                    .collect(Collectors.toList());
        }

        AccidentType type = (types.findById(typeId)).get();

        accident.setType(type);
        accident.setRules(rules);
        accidents.save(accident);
        return "redirect:/";
    }
}
