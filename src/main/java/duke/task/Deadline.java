package duke.task;

import duke.command.Command;

import java.util.Date;

public class Deadline extends Task {

    /** Deadline Time(with date). */
    private Date deadlineTime;

    /**
     * Constructs the Deadline object.
     *
     * @param deadlineName Name of deadline
     * @param deadlineTime Deadline Time (with date)
     */
    public Deadline(String deadlineName, Date deadlineTime) {
        super(deadlineName);
        this.deadlineTime = deadlineTime;
    }

    /**
     * Returns the Deadline name.
     *
     * @return Deadline name
     */
    @Override
    public String getTaskName() {
        return super.getTaskName()
                + " (by: " + Command.DATE_FORMAT.format(deadlineTime) + ")";
    }

    /**
     * Returns the Representing Letter to distinguish the Task types.
     *
     * @return Representing Letter
     */
    @Override
    public char getRepLetter() {
        return 'D';
    }


    /**
     * Returns a bar delimited string for storage-related purposes.
     *
     * @return Bar delimited string
     */
    @Override
    public String toDelimitedString() {
        return String.format("%c | %c | %s | %s", this.getRepLetter(), this.isDone() ? 'T' : 'F', super.getTaskName(),
                Command.DATE_FORMAT.format(this.deadlineTime));
    }
}
