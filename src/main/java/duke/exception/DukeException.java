package duke.exception;

public class DukeException extends Exception {

    /**
     * Initialises a DukeException.
     *
     * @param message The error message
     */
    public DukeException(String message) {
        super("☹ OOPS!!! " + message);
    }

    /**
     * Returns an example of the correct format for the specific command.
     *
     * @param command The command to return a format for.
     * @return The correct user input format.
     */
    static String getCorrectFormat(String command) {
        switch (command) {
        case "todo":
            return "e.g. todo make bed";
        case "deadline":
            return "e.g. deadline assignment 3 /by 11/12/2019 1500";
        case "event":
            return "e.g. event assignment 3 /at 11/12/2019 1500 - 12/12/2019 2000";
        case "done":
        case "delete":
            return "e.g. " + command + " 3";
        case "find":
            return "e.g. find book";
        case "list":
            return "list";
        case "sort":
            return "e.g. sort name";
        default:
            return null;
        }
    }

}
