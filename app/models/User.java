package cards.models;

/**
 * Created by alexandra on 9/9/15.
 */
public class User {

    private final String login;
    private final String email;

    public User(String login, String email) {
        this.login = login;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

}
