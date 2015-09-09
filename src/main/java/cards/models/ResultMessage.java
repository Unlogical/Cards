package cards.models;

/**
 * Created by alexandra on 9/9/15.
 */
public class ResultMessage {

    private final String status;
    private final String message;

    public ResultMessage(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
