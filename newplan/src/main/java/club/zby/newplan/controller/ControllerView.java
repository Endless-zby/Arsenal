package club.zby.newplan.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("view")
public class ControllerView {

    @GetMapping("index")
    public String viewIndex(){
        return "view";
    }

}
