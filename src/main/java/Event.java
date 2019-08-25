public class Event extends Task {
    String eventDuration;

    /**
     * Event task
     * @param description user input of the title of the task
     * @param eventDuration user input of the duration of the event the format is d/MM/yyyy HHmm-HHmm
     */
    public Event(String description, String eventDuration) {
        super(description);
        this.eventDuration = eventDuration;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[E][\u2713] " + this.description + " (at: " + this.eventDuration + ")";
        } else {
            return "[E][\u2718] " + this.description + " (at: " + this.eventDuration + ")";
        }
    }

    @Override
    public String createTaskInFileFormat() {
        String temp = "E ";
        temp += super.createTaskInFileFormat();
        temp += " /at ";
        temp += this.eventDuration;
        return temp;
    }
}
