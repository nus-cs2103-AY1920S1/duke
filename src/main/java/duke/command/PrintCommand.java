package duke.command;

public class PrintCommand implements Command {
    /**
     * Returns the task type.
     *
     * @return List.
     */
    public String getTaskType() {
        return "list";
    };

    public int getIndex() {
        return 0;
    };

    public String getTask() {
        return "";
    };

    public String getDate() {
        return "";
    };
}
