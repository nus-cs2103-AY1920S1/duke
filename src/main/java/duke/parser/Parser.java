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
    public int getCommand(String input) {
        String[] i = input.split("\\s");
        if (i[0].equals("bye")) {
            return 0;
        } else if (i[0].equals("list")) {
            return 1;
        } else if (i[0].equals("done")) {
            return 2;
        } else if (i[0].equals("delete")) {
            return 3;
        } else if (i[0].equals("todo")) {
            return 4;
        } else if (i[0].equals("deadline")) {
            return 5;
        } else if (i[0].equals("event")) {
            return 6;
        } else if (i[0].equals("find")) {
            return 7;
        } else {
            return -1;
        }
    }
}
