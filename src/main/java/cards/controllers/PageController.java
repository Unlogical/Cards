package cards.controllers;

import cards.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;

/**
 * Created by alexandra on 11/21/15.
 */
@Controller
public class PageController {

    @Autowired
    private SessionManager sessionManager;

    @RequestMapping(value = "/")
    public String index(ModelMap model, @CookieValue(value = "sid", defaultValue = "") String sessionId){
        try {
            int uuid = sessionManager.sessionToId(sessionId);
            if(!sessionId.isEmpty() && uuid >= 0){
                return "userpage";
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "index";
    }

    @RequestMapping(value = "/userpage")
    public String userpage(ModelMap model, @CookieValue(value = "sid", defaultValue = "") String sessionId){
        return "userpage";
    }

}

