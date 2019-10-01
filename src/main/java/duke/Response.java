package duke;

/**
 * This class serves as a response from the Duke Logic class.
 */
public class Response {
    public String message;
    public boolean isExit;

    public Response(String message, boolean isExit) {
        this.message = message;
        this.isExit = isExit;
    }
}