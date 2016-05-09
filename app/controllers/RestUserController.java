package cards.controllers;

import cards.SessionManager;
import cards.Users;
import cards.models.ResultMessage;
import cards.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by alexandra on 9/9/15.
 */
@RestController
public class RestUserController {

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    private Users users;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResultMessage getUser(String login){
        User user = users.getUser(login);
        if(user != null){
            return new ResultMessage("ok", "user", user);
        }
        return new ResultMessage("fail", "user not found", null);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResultMessage signUp(HttpServletResponse response, String login, String password, String email){
        System.out.println("New user signup");
        if(users.addUser(login, password, email)){
            String sessionId = null;
            try {
                int userId = users.loginToId(login);
                sessionId = sessionManager.createSession(userId);
                System.out.println("User signin success with session " + sessionId);
                response.addCookie(new Cookie("sid", sessionId));
                System.out.println("New user signup done");
                return new ResultMessage("ok", "user created", null);
            } catch (SQLException | ClassNotFoundException e) {
                System.out.println("New user signup failed (internal error)");
                e.printStackTrace();
                return new ResultMessage("fail", "user not created due to internal error", null);
            }
        }
        System.out.println("New user signup failed");
        return new ResultMessage("fail", "user not created", null);
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResultMessage signIn(HttpServletResponse response, String login, String password){
        System.out.println("User signin: " + login + ":" + password);
        if(users.checkPassword(login, password)){
            try {
                int userId = users.loginToId(login);
                String sessionId = sessionManager.createSession(userId);
                System.out.println("User signin success with session " + sessionId);
                response.addCookie(new Cookie("sid", sessionId));
                return new ResultMessage("ok", "Success", sessionId);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        System.out.println("User signin failed");
        return  new ResultMessage("fail", "Fail", null);
    }

}
