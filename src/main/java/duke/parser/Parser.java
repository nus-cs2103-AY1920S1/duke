package duke.parser;

import duke.parser.Comd;

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
    /*
    public int getCommand(String input) {
        String[] i = input.split("\\s");
        switch (i[0]) {
            case "bye":
                return 0;
            case "list":
                return 1;
            case "done":
                return 2;
            case "delete":
                return 3;
            case "todo":
                return 4;
            case "deadline":
                return 5;
            case "event":
                return 6;
            case "find":
                return 7;
            default:
                return -1;
        }
    }*/

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
