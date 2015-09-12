package cards;

import cards.models.User;
import java.sql.*;
import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;

/**
 * Created by alexandra on 9/9/15.
 */
public class Users {

    private static Connection connection = connect("jdbc:postgresql://192.168.56.101/resu_db", "resu", "resu");

    private static Connection connect(String connectionString, String username, String password){
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(connectionString, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static User getUser(String login){

        try {
            ResultSet resultSet = connection.createStatement().executeQuery("select * from users where login='" + login + "'"); // TODO: use prepared statements  ( '; drop table lol;-- )
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
            PreparedStatement insertUser = connection.prepareStatement("insert into users(login, passwd, email, gender) values (?,?,?,?,?)");
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

}