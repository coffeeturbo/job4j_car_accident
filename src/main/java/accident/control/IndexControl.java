package accident.control;


import accident.repository.AccidentMem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model) {
        AccidentMem repository = new AccidentMem();
        model.addAttribute("accidents", repository.getAccidents());
        return "index";
    }
}
