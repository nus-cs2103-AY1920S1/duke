package duke.command;

public class EventCommand implements Command {
    private String task;
    private String eventDate;

    /**
     * Constructs an event command based on the input string,
     * and store it as task description and event date.
     *
     * @param input Input string.
     */
    public EventCommand(String input) {
        String[] processedInput = input.split("/by");

        this.task = processedInput[0];
        this.eventDate = processedInput[1];
    }

    /**
     * Returns task type.
     *
     * @return Event.
     */
    public String getTaskType() {
        return "event";
    }

    public int getIndex() {
        return 0;
    }

    /**
     * Returns the event description of this event.
     *
     * @return Description of this event.
     */
    public String getTask() {
        return task;
    }

    /**
     * Returns the event date of this event.
     *
     * @return Event date.
     */
    public String getDate() {
        return eventDate;
    }

    public String getKeyword() {
        return "error";
    }
}
