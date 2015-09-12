package cards.models;

/**
 * Created by alexandra on 9/9/15.
 */
public class User {

    private final String login;
    private final String email;
    private final boolean gender;

    public User(String login, String email, boolean gender) {
        this.login = login;
        this.email = email;
        this.gender = gender;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public boolean isGender() {
        return gender;
    }
}
