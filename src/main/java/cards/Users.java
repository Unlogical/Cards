package cards;

import cards.models.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

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
            ResultSet resultSet = connection.createStatement().executeQuery("select * from users where login='" + login + "'");
            while(resultSet.next()){
                String email = resultSet.getString("email");
                Date birthdate = resultSet.getDate("birth_date");
                Boolean gender = resultSet.getBoolean("gender");
                return new User(login, email, gender, birthdate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

         return null;

    }

    public static boolean addUser(String login, String passwd, String email, boolean gender, Date birthDate) {
        return true;
    }

}