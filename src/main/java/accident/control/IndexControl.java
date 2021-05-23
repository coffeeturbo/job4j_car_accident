package accident.control;


import accident.repository.AccidentJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexControl {
    private final AccidentJdbcTemplate accidents;

    public IndexControl(AccidentJdbcTemplate accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("accidents", accidents.getAll());
        return "index";
    }
//    private final AccidentMem accidents;
//
//    public IndexControl(AccidentMem accidents) {
//        this.accidents = accidents;
//    }
//
//    @GetMapping("/")
//    public String index(Model model) {
//        model.addAttribute("accidents", accidents.getAccidents());
//        return "index";
//    }
}
