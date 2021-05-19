package accident.control;

import accident.model.Accident;
import accident.repository.AccidentMem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccidentControl {
    private final AccidentMem accidents;

    public AccidentControl(AccidentMem accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/create")
    public String create() {
        return "accident/create";
    }
    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {

        model.addAttribute("accident", accidents.findById(id));
        return "accident/update";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident) {
        accidents.save(accident);
        return "redirect:/";
    }
}
