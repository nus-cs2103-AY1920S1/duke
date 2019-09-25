package duke.parser;


public class Parser {

    /**
     * Checks if command is "todo".
     */

    public boolean isTodo(String[] tokens) {
        return (tokens[0].equals("todo") || tokens[0].equals("t"));
    }

    /**
     * Checks if command is "deadline".
     */

    public boolean isDeadline(String[] tokens) {
        return (tokens[0].equals("deadline") || tokens[0].equals("dl"));
    }

    /**
     * Checks if command is "event".
     */

    public boolean isEvent(String[] tokens) {
        return (tokens[0].equals("event") || tokens[0].equals("e"));
    }

    /**
     * Checks if command is "delete".
     */

    public boolean isDelete(String[] tokens) {
        return (tokens[0].equals("delete") || tokens[0].equals("dlt"));
    }

    /**
     * Checks if command is "done".
     */

    public boolean isDone(String[] tokens) {
        return (tokens[0].equals("done") || tokens[0].equals("d"));
    }

    /**
     * Checks if command is "list".
     */

    public boolean isList(String command) {
        return (command.equals("list") || command.equals("l"));
    }

    /**
     * Checks if command is "bye".
     */

    public boolean isBye(String command) {
        return (command.equals("bye") || command.equals("b"));
    }

    public boolean isFind(String[] command) {
        return (command.equals("find") || command.equals("f"));
    }

}
