package duke.command;

public class DeleteCommand implements Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public String getTaskType() {
        return "delete";
    };

    public int getIndex() {
        return index;
    };

    public String getTask() {
        return "";
    };

    public String getDate() {
        return "";
    };
}
