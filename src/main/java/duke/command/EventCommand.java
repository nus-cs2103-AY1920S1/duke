package duke.command;

public class EventCommand implements Command {
    private String task;
    private String eventDate;

    public EventCommand(String input) {
        String[] processedInput = input.split("/by");

        this.task = processedInput[0];
        this.eventDate = processedInput[1];
    }

    public String getTaskType() {
        return "event";
    }

    public int getIndex() {
        return 0;
    }

    public String getTask() {
        return task;
    }

    public String getDate() {
        return eventDate;
    }
}
