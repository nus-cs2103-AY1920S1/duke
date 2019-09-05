package duke.parser;


public class Parser {

    /**
     * Checks if command is "todo".
     */

    public boolean isTodo(String[] tokens) {
        return tokens[0].equals("todo");
    }

    /**
     * Checks if command is "deadline".
     */

    public boolean isDeadline(String[] tokens) {
        return tokens[0].equals("deadline");
    }

    /**
     * Checks if command is "event".
     */

    public boolean isEvent(String[] tokens) {
        return tokens[0].equals("event");
    }

    /**
     * Checks if command is "delete".
     */

    public boolean isDelete(String[] tokens) {
        return tokens[0].equals("delete");
    }

    /**
     * Checks if command is "done".
     */

    public boolean isDone(String[] tokens) {
        return tokens[0].equals("done");
    }

    /**
     * Checks if command is "list".
     */

    public boolean isList(String command) {
        return (command.equals("list"));
    }

    /**
     * Checks if command is "bye".
     */

    public boolean isBye(String command) {
        return (command.equals("bye"));
    }

    public boolean isFind(String[] command) {
        return (command.equals("find"));
    }

}
