package cards.controllers;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by alexandra on 11/21/15.
 */
@Controller
public class PageController {

    @RequestMapping(value = "/")
    public String index(ModelMap model, @CookieValue(value = "sid", defaultValue = "") String sessionId){
//        model.addAttribute("userName", "Fluttershy");
        if(sessionId.isEmpty()){
            return "index";
        }
        return "userpage";
    }

    @RequestMapping(value = "/userpage")
    public String userpage(ModelMap model, @CookieValue(value = "sid", defaultValue = "") String sessionId){
        return "userpage";
    }

}

