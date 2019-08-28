package duke.command;

public class TodoCommand implements Command {
    private String taskType = "todo";
    private String task;

    public TodoCommand(String task) {
        this.task = task;
    }

    public String getTaskType() {
        return "todo";
    }

    public int getIndex() {
        return 0;
    }

    public String getTask() {
        return task;
    }

    public String getDate() {
        return "error";
    }

    public String getKeyword() {
        return "error";
    }
}
