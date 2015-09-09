package cards.models;

import java.util.Date;

/**
 * Created by alexandra on 9/9/15.
 */
public class User {

    private final String login;
    private final String email;
    private final String passwd;
    private final boolean gender;
    private final Date birthDate;

    public User(String login, String email, String passwd, boolean gender, Date birthDate) {
        this.login = login;
        this.email = email;
        this.passwd = passwd;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswd() {
        return passwd;
    }

    public boolean isGender() {
        return gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }
}
