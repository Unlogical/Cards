package cards;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static cards.ConnectionProvider.*;
import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;

/**
 * Created by alexandra on 9/24/15.
 */
public class SessionManager {

    private SessionManager(){}
    public static SessionManager sessionManager = new SessionManager();
    public static SessionManager getSessionManager(){
        return sessionManager;
    }

    public String createSession(String login) throws SQLException, ClassNotFoundException {
        int userId = loginToId(login);
        String sessionID = sha256Hex(login + System.currentTimeMillis());
        PreparedStatement preparedStatement = getConnectionProvider().getConnection().prepareStatement("insert into sessions(user_id, session_id) values(?,?)");
        preparedStatement.setInt(1, userId);
        preparedStatement.setString(2, sessionID);
        preparedStatement.execute();
        return sessionID;
    }

    private int loginToId(String login) throws SQLException, ClassNotFoundException {
        Connection cnct = getConnectionProvider().getConnection();
        PreparedStatement preparedStatement = cnct.prepareStatement("select id from users where login = ?");
        preparedStatement.setString(1,login);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            return resultSet.getInt("id");
        }
        return -1;
    }

}
