package cards.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by alexandra on 11/21/15.
 */
@Controller
public class PageController {

    @RequestMapping(value = "/")
    public String index(ModelMap model){
        model.addAttribute("userName", "Fluttershy");
        return "index";
    }

}
