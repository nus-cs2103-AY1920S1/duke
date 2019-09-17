package duke.command;

/**
 * Class representing a command that greets the user back
 * if they issue a greeting.
 */
public class HelloCommand extends Command {
    public static final String KEYWORD_ONE = "hello";
    public static final String KEYWORD_TWO = "hi";
    public static final String KEYWORD_THREE = "hey";

    /**
     * Prints out a response when greeted by the user.
     *
     * @return String containing a hello response.
     */
    @Override
    public String execute() {
        return this.ui.HELLO_RESPONSE;
    }
}
