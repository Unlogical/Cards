package cards;
import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;

/**
 * Created by alexandra on 9/24/15.
 */
public class SessionManager {
    public  static String createSession(String login){
        String sessionID = sha256Hex(login + System.currentTimeMillis());
        return null;
    }
}
