package accident.control;


import accident.repository.AccidentHibernate;
import accident.repository.AccidentJdbcTemplate;
import accident.repository.AccidentRepository;
import accident.repository.Repository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexControl {
    private final AccidentRepository accidents;

    public IndexControl(AccidentRepository accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("accidents", accidents.findAll());
        return "index";
    }
}
