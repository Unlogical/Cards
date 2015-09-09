package cards;

import cards.models.User;

import java.util.Date;

/**
 * Created by alexandra on 9/9/15.
 */
public class Users {

    public static User getUser(String login){
        return new User(login, login + "@lol.com", true, new Date(System.currentTimeMillis()));
    }

    public static boolean addUser(String login, String passwd, String email, boolean gender, Date birthDate) {
        return true;
    }
}
