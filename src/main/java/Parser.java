public class Parser {

    private String fullCommand;
    private String[] part;

    public Parser (String fullCommand) {
        this.fullCommand = fullCommand;
        this.part = fullCommand.split(" ");
    }

    public String getCommandType() {
        return part[0];
    }

    public int getIndex() {
        return Integer.parseInt(part[1]);
    }

    public String getActivityNameWithoutTime() {
        return fullCommand.substring(fullCommand.indexOf(" ")+1);
    }

    public String getActivityNameWithTime() {
        return fullCommand.substring(fullCommand.indexOf(" ")+1, fullCommand.indexOf("/"));
    }

    public String getDeadline() {
        String[] deadline = fullCommand.split("by");
        return deadline[1];
    }

    public String getTime() {
        String[] time = fullCommand.split("at");
        return time[1];
    }

    public boolean checkValidity() throws DukeException {
        switch (getCommandType()) {
            case "list":
                break;
            case "done": case "delete":
                if (part.length < 2) {
                    throw new DukeException("☹ OOPS!!! You need to enter an index.");
                }
            case "todo":
                // check task description.
                if (part.length < 2) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                break;
            case "deadline":
                if (part.length < 2) {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
                break;
            case "event":
                if (part.length < 2) {
                    throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                }
                break;
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return true;
    }
}
