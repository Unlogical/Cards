package cards.models;

/**
 * Created by alexandra on 9/9/15.
 */
public class ResultMessage {

    private final String status;
    private final String message;
    private final Object data;

    public ResultMessage(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
