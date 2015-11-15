package cards;

import cards.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;

/**
 * Created by alexandra on 9/9/15.
 */
public class Users {

    private final ConnectionProvider connectionProvider;

    public Users(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public User getUser(String login) {

        try {
            PreparedStatement getuser = connectionProvider.getConnection().prepareStatement("select * from users where login =? ");
            getuser.setString(1,login);
            ResultSet resultSet = getuser.executeQuery();
            if(resultSet.next()){
                String email = resultSet.getString("email");
                Boolean gender = resultSet.getBoolean("gender");
                return new User(login, email, gender);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
         return null;
    }

    public boolean addUser(String login, String passwd, String email, boolean gender) {
        //TODO check this data
        String[] data = new String[3];
        data[0] = login;
        data[1] = passwd;
        data[2] = email;
        for(String str : data){
            if(str == null || str == "" ) return false;
        }
//        public static boolean checkWithRegExp(String mail){
//            Pattern p = Pattern.compile("^[a-z0-9_-]{3,15}$");
//            Matcher m = p.matcher(mail);
//            return m.matches();
//        }
//        if( email != )


        try {
            PreparedStatement insertUser = connectionProvider.getConnection().prepareStatement("insert into users(login, passwd, email, gender) values (?,?,?,?)");
            insertUser.setString(1, login);
            insertUser.setString(2, sha256Hex(passwd));
            insertUser.setString(3, email);
            insertUser.setBoolean(4, gender);
            insertUser.execute();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkPassword(String login, String password){
        String passwordHash = sha256Hex(password);
        try {
            PreparedStatement checkPasswd = connectionProvider.getConnection().prepareStatement("select passwd from users where login=?");
            checkPasswd.setString(1, login);
            ResultSet resultSet = checkPasswd.executeQuery();
            if(resultSet.next()) {
                System.out.println("Checking password");
                String basePasswordHash = resultSet.getString(1);
                return basePasswordHash.equals(passwordHash);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Checking failed or bad password");
        return false;
    }

}

