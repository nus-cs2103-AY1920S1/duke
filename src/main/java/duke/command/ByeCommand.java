package duke.command;

public class ByeCommand implements Command {
    public String getTaskType() {
        return "bye";
    }

    public int getIndex() {
        return 0;
    }

    public String getTask() {
        return "error";
    }

    public String getDate() {
        return "error";
    }

    public String getKeyword() {
        return "error";
    }
}
