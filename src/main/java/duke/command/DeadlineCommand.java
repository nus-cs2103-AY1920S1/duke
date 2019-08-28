package duke.command;

public class DeadlineCommand implements Command {
    private String task;
    private String deadline;

    /**
     * Generates deadline command based on the input string.
     * Processes the input string and store it as task and deadline.
     *
     * @param input Input string.
     */
    public DeadlineCommand(String input) {
        String[] processedInput = input.split("/by");

        this.task = processedInput[0];
        this.deadline = processedInput[1];
    }

    /**
     * Returns task type.
     *
     * @return Deadline.
     */
    public String getTaskType() {
        return "deadline";
    }

    /**
     * Returns the deadline.
     *
     * @return Date of the deadline.
     */
    public String getDate() {
        return deadline;
    }

    public int getIndex() {
        return 0;
    }

    public String getTask() {
        return task;
    }

    public String getKeyword() {
        return "error";
    }
}
