package cards.controllers;

import cards.SessionManager;
import cards.models.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by alexandra on 11/29/15.
 */

@RestController
public class RestSetController {

    @Autowired
    private SessionManager sessionManager;

    @RequestMapping(value = "create_set", method = RequestMethod.POST)
    public ResultMessage createSet(@CookieValue(value = "sid", defaultValue = "") String sessionId) {


      return null;
    }
}
