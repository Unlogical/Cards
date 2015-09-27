package cards;

import org.postgresql.jdbc2.optional.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by alexandra on 9/27/15.
 */
public class ConnectionProvider {

    private static ConnectionProvider connectionProvider = new ConnectionProvider();
    private ConnectionPool pool;

    private ConnectionProvider(){}

    public static ConnectionProvider getConnectionProvider(){
        return connectionProvider;
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        if(pool == null){
            synchronized (ConnectionProvider.class){
                if (pool == null){
                    pool = connect("jdbc:postgresql://192.168.56.101/resu_db", "resu", "resu"); //TODO: move to config
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