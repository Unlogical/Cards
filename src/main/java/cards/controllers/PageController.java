package cards.controllers;

import cards.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        if (sessionManager.sessionExists(sessionId)) {
            return "userpage";
        } else {
            return "index";
        }
    }

    @RequestMapping(value = "/add")
    public String add(ModelMap model, @CookieValue(value = "sid", defaultValue = "") String sessionId){
        if (sessionManager.sessionExists(sessionId)) {
            return "add";
        } else {
            return "index";
        }
    }

    @RequestMapping(value = "/create_set", method = RequestMethod.GET)
    public String create_set(ModelMap model, @CookieValue(value = "sid", defaultValue = "") String sessionId){
        if (sessionManager.sessionExists(sessionId)) {
            return "create_set";
        } else {
            return "index";
        }
    }

    @RequestMapping(value = "/edit_set")
    public String edit_set(ModelMap model, @CookieValue(value = "sid", defaultValue = "") String sessionId){
        if (sessionManager.sessionExists(sessionId)) {
            return "edit_set";
        } else {
            return "index";
        }
    }

    @RequestMapping(value = "/category")
    public String category(ModelMap model, @CookieValue(value = "sid", defaultValue = "") String sessionId){
        if (sessionManager.sessionExists(sessionId)) {
            return "category";
        } else {
            return "index";
        }
    }

    @RequestMapping(value = "/learn")
    public String learn(ModelMap model, @CookieValue(value = "sid", defaultValue = "") String sessionId){
        if (sessionManager.sessionExists(sessionId)) {
            return "learn";
        } else {
            return "index";
        }
    }

    @RequestMapping(value = "/learn2")
    public String learn2(ModelMap model, @CookieValue(value = "sid", defaultValue = "") String sessionId){
        if (sessionManager.sessionExists(sessionId)) {
            return "learn2";
        } else {
            return "index";
        }
    }


    @RequestMapping(value = "/exit", method = RequestMethod.GET)
    public String exit(@CookieValue("sid") String sessionId){
        try {
            sessionManager.deleteSession(sessionId);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "index";
    }

}

