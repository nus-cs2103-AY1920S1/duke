package duke.ui;

/**
 * Class representing response returned by Duke's logic
 */
public class Response {
    private boolean isExit = false;
    private String message;

    /**
     * Class constructor specifying message to be output and boolean indicating
     * whether response is to exit.
     *
     * @param message Message to output.
     * @param isExit Type of response (exit or not exit).
     */
    public Response(String message, boolean isExit){
        this.message = message;
        this.isExit = isExit;
    }

    /**
     * Class constructor specifying only message to output, is assumed to not
     * be an exit response.
     *
     * @param message Message to output.
     */
    public Response(String message){
        this.message = message;
    }

    /**
     * Returns message to be printed.
     *
     * @return Message to be printed.
     */
    public String getMessage(){
        return message;
    }

    /**
     * Returns a true or false indicating whether response is an exit.
     *
     * @return True or False depending if response is an exit.
     */
    public boolean isExitResponse(){
        return isExit;
    }
}
