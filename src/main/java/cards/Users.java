package cards;

import cards.models.User;
import org.postgresql.jdbc2.optional.ConnectionPool;

import java.sql.*;
import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;

/**
 * Created by alexandra on 9/9/15.
 */
public class Users {

    public static User getUser(String login){

        try {
            ResultSet resultSet = connectionPool.getConnection().createStatement().executeQuery("select * from users where login='" + login + "'"); // TODO: use prepared statements  ( '; drop table lol;-- )
            while(resultSet.next()){
                String email = resultSet.getString("email");
                Boolean gender = resultSet.getBoolean("gender");
                return new User(login, email, gender);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

         return null;
    }

    public static boolean addUser(String login, String passwd, String email, boolean gender) {
        //TODO check this data
        try {
            PreparedStatement insertUser = connectionPool.getConnection().prepareStatement("insert into users(login, passwd, email, gender) values (?,?,?,?,?)");
            insertUser.setString(1, login);
            insertUser.setString(2, sha256Hex(passwd));
            insertUser.setString(3, email);
            insertUser.setBoolean(4, gender);
            return insertUser.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean checkPassword(String login, String password){
        String passwordHash = sha256Hex(password);
        try {
            PreparedStatement checkPasswd = connectionPool.getConnection().prepareStatement("select passwd from users where login=?");
            checkPasswd.setString(1, login);
            ResultSet resultSet = checkPasswd.executeQuery();

            if(resultSet.next()) {
                System.out.println("Checking password");
                String basePasswordHash = resultSet.getString(1);
                return basePasswordHash.equals(passwordHash);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Checking failed or bad password");
        return false;
    }

}

