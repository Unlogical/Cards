package cards.controllers;

import cards.Users;
import cards.models.ResultMessage;
import cards.models.User;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

import static cards.SessionManager.getSessionManager;

/**
 * Created by alexandra on 9/9/15.
 */
@RestController
public class UsersController {

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResultMessage getUser(String login){
        User user = Users.getUser(login);
        if(user != null){
            return new ResultMessage("ok", "user", user);
        }
        return new ResultMessage("fail", "user not found", null);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResultMessage signUp(String login, String passwd, String email, boolean gender){
        if(Users.addUser(login, passwd, email, gender)){
            return new ResultMessage("ok", "user created", null);
        }
        return new ResultMessage("fail", "user not created", null);
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResultMessage signIn(HttpServletResponse response, String login, String password){
        System.out.println("User signin: " + login + ":" + password);
        if(Users.checkPassword(login, password)){
            try {
                String sessionId = getSessionManager().createSession(login);
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
            getSessionManager().deleteSession(sessionId);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/fakesignup", method = RequestMethod.POST)
    public ResultMessage fakeSignUp(String login, String passwd, String email, boolean gender){
        System.out.println("login = [" + login + "], passwd = [" + passwd + "], email = [" + email + "], gender = [" + gender + "]");
        return new ResultMessage("OK", "Fine", null);
    }

}
