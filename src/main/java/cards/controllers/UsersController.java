package cards.controllers;

import cards.Users;
import cards.models.ResultMessage;
import cards.models.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResultMessage addUser(String login, String passwd, String email, boolean gender){
        if(Users.addUser(login, passwd, email, gender)){
            return new ResultMessage("ok", "user created", null);
        }
        return new ResultMessage("fail", "user not created", null);
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResultMessage signin(String login, String password){
        System.out.println("User signin: " + login + ":"+password);
        if(Users.checkPassword(login, password)){
            return new ResultMessage("ok", "Success", null);
        }
        return  new ResultMessage("fail", "Fail", null);
        // 1. Найти такой логин
        // 2. Сравнить пароли
        // 3. Если все хорошо,то выполнить вход
        // 4. Оставатьься в системе
    }


}
