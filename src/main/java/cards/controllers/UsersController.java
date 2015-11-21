package cards.controllers;

import cards.SessionManager;
import cards.Users;
import cards.models.ResultMessage;
import cards.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * Created by alexandra on 9/9/15.
 */
@Controller
public class UsersController {

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
    public ResultMessage signUp(String login, String passwd, String email, boolean gender){
        if(users.addUser(login, passwd, email, gender)){
            return new ResultMessage("ok", "user created", null);
        }
        return new ResultMessage("fail", "user not created", null);
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResultMessage signIn(HttpServletResponse response, String login, String password){
        System.out.println("User signin: " + login + ":" + password);
        if(users.checkPassword(login, password)){
            try {
                String sessionId = sessionManager.createSession(login);
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

    @RequestMapping(value = "/signout", method = RequestMethod.GET)
    public void signOut(@CookieValue("sid") String sessionId){
        try {
            sessionManager.deleteSession(sessionId);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/fakesignup", method = RequestMethod.POST)
    public ResultMessage fakeSignUp(String login, String passwd, String email, boolean gender){
        System.out.println("login = [" + login + "], passwd = [" + passwd + "], email = [" + email + "], gender = [" + gender + "]");
        return new ResultMessage("OK", "Fine", null);
    }

    @RequestMapping(value = "/")
    public String index(){
        System.out.println("LOL");
        return "index";
    }

}
