package duke.command;

public class DoneCommand implements Command {
    private String taskType = "done";
    private int index;

    /**
     * Constructs a done command based on the index input.
     *
     * @param index Index of the task done.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Generates task type.
     *
     * @return Done.
     */
    public String getTaskType() {
        return "done";
    }

    /**
     * Returns the index of the task done.
     *
     * @return Index of the task done.
     */
    public int getIndex() {
        return index;
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
