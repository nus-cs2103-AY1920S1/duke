package duke.parser;

/**
 * Class for parsing user input.
 */
public class Parser {
    public Parser() {
    }

    /**
     * Gets a command from the user.
     *
     * @param input original String input from user
     * @return an integer that represents a command
     */
    public Comd getCommand(String input) {
        String[] i = input.split("\\s");
        switch (i[0]) {
            case "bye":
                return Comd.BYE;
            case "list":
                return Comd.LIST;
            case "done":
                return Comd.DONE;
            case "delete":
                return Comd.DELETE;
            case "todo":
                return Comd.TODO;
            case "deadline":
                return Comd.DEADLINE;
            case "event":
                return Comd.EVENT;
            case "find":
                return Comd.FIND;
            default:
                return Comd.NULL;
        }
    }
}
