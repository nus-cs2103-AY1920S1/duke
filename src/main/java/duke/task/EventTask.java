package duke.task;

import duke.date.DukeDate;

public class EventTask extends Task {

    private DukeDate time;

    public EventTask(String description, DukeDate time) {
        super(description);
        this.time = time;
    }

    public EventTask(String description, boolean isDone, DukeDate time) {
        super(description, isDone);
        this.time = time;
    }

    public DukeDate getTime() {
        return this.time;
    }

    public String getDateAsString() {
        return this.time.format();
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String getStatus() {
        return String.format("[E]%s (at: %s)", super.getStatus(), this.time);
    }

}
