package cards;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;

/**
 * Created by alexandra on 9/24/15.
 */
public class SessionManager {

    private ConnectionProvider connectionProvider;

    public SessionManager(ConnectionProvider connectionProvider){
        this.connectionProvider = connectionProvider;
    }

    public String createSession(long userId) throws SQLException, ClassNotFoundException {
        String sessionID = sha256Hex(userId + "-" + System.currentTimeMillis());
        PreparedStatement preparedStatement = connectionProvider.getConnection().prepareStatement("insert into sessions(user_id, session_id) values(?,?)");
        preparedStatement.setLong(1, userId);
        preparedStatement.setString(2, sessionID);
        preparedStatement.execute();
        return sessionID;
    }

    public void deleteSession(String sessionId) throws SQLException, ClassNotFoundException {
        Connection connection = connectionProvider.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("delete from sessions where session_id = ?");
        preparedStatement.setString(1,sessionId);
        preparedStatement.execute();
    }

    public void deleteAllUserSessions(int userId) throws SQLException, ClassNotFoundException {
        Connection connection = connectionProvider.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("delete from sessions where user_id = ?");
        preparedStatement.setInt(1,userId);
        preparedStatement.execute();
    }

    public int sessionToId(String sessionId) throws SQLException, ClassNotFoundException {
        Connection connection = connectionProvider.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select user_id from sessions where session_id = ?");
        preparedStatement.setString(1,sessionId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            return resultSet.getInt("user_id");
        }
        return  -1;
    }

    public boolean sessionExists(String sessionId) {
        try {
            int uuid = this.sessionToId(sessionId);
            if(!sessionId.isEmpty() && uuid >= 0){
                return true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
