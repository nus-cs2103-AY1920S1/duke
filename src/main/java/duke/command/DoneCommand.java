package duke.command;

public class DoneCommand implements Command {
    private String taskType = "done";
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    public String getTaskType() {
        return "done";
    }

    public int getIndex() {
        return index;
    }

    public String getTask() {
        return "error";
    }

    public String getDate() {
        return "error";
    }
}
