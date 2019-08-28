package duke.command;

public class DeadlineCommand implements Command {
    private String task;
    private String deadline;

    public DeadlineCommand(String input) {
        String[] processedInput = input.split("/by");

        this.task = processedInput[0];
        this.deadline = processedInput[1];
    }

    public String getTaskType() {
        return "deadline";
    }

    public String getDate() {
        return deadline;
    }

    public int getIndex() {
        return 0;
    }

    public String getTask() {
        return task;
    }
}
