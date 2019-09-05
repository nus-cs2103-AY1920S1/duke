public class Parser {
    public boolean isTodo(String[] tokens) {
        return tokens[0].equals("todo");
    }

    public boolean isDeadline(String[] tokens) {
        return tokens[0].equals("deadline");
    }

    public boolean isEvent(String[] tokens) {
        return tokens[0].equals("event");
    }

    public boolean isDelete(String[] tokens) {
        return tokens[0].equals("delete");
    }

    public boolean isDone(String[] tokens) {
        return tokens[0].equals("done");
    }

    public boolean isList(String command) {
        return (command.equals("list"));
    }

    public boolean isBye(String command) {
        return (command.equals("bye"));
    }
}
