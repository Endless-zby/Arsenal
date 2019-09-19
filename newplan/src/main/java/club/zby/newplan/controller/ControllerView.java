package club.zby.newplan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Controller
@RequestMapping(value = "pages")
public class ControllerView {

    @GetMapping(value = "mainview")
    public String viewIndex(){
        return "view";
    }

}
