package cards.controllers;

import cards.Users;
import cards.models.ResultMessage;
import cards.models.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by alexandra on 9/9/15.
 */
@RestController
public class UsersController {

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User getUser(String login){
        return Users.getUser(login);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResultMessage addUser(String login, String passwd, String email, boolean gender, Date birthDate){

        if(Users.addUser(login, passwd, email, gender, birthDate)){
            return new ResultMessage("ok", "user created");
        }
        return new ResultMessage("fail", "user not created");
    }


}
