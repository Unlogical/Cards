package cards;

import org.postgresql.jdbc2.optional.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Struct;

/**
 * Created by alexandra on 9/27/15.
 */
public class ConnectionProvider {

    private ConnectionPool pool;
    private final String userName;
    private final String password;
    private final String connectionString;

    public ConnectionProvider(String serverAddress, int serverPort, String dbName, String userName, String password) {
        this.connectionString = "jdbc:postgresql://" + serverAddress + ":" + serverPort + "/" + dbName;
        this.userName = userName;
        this.password = password;
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        if(pool == null){
            synchronized (ConnectionProvider.class){
                if (pool == null){
                    pool = connect(connectionString, userName, password); //TODO: move to config
                }
            }
        }
        return pool.getConnection();
    }

    private ConnectionPool connect(String connectionString, String username, String password) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        ConnectionPool pool = new ConnectionPool();
        pool.setUrl(connectionString);
        pool.setUser(username);
        pool.setPassword(password);
        return pool;
    }

}