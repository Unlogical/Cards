package cards.controllers;

import cards.Users;
import cards.models.User;
import cards.models.UserId;
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
    public UserId addUser(String login, String passwd, String email, boolean gender, Date birthDate){
        return new UserId(Users.addUser(login, passwd, email, gender, birthDate));
    }

}
