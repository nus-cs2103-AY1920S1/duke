package duke.task;

import duke.date.DukeDate;

public class DeadlineTask extends Task {

    private DukeDate dueDate;

    public DeadlineTask(String description, DukeDate dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    public DeadlineTask(String description, boolean isDone, DukeDate dueDate) {
        super(description, isDone);
        this.dueDate = dueDate;
    }

    public DukeDate getDueDate() {
        return this.dueDate;
    }

    public String getDateAsString() {
        return this.dueDate.format();
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String getStatus() {
        return String.format("[D]%s (by: %s)", super.getStatus(), this.dueDate);
    }

}
