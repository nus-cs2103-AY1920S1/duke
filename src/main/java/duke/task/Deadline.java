package duke.task;

import duke.Storage;
import duke.command.Command;

public class Deadline extends TimeTask {
    Deadline(String description, String time) {
        super(description, time);
    }

    /**
     * Returns a Command which generates a Deadline given the input line.
     *
     * @param tasks The shared list of tasks.
     * @param storage Storage to save the tasks after adding the deadline.
     * @return A Command which generates deadlines.
     */
    public static Command getCommand(TaskList tasks, Storage storage) {
        return getCommand(tasks, storage, "by", "a deadline", Deadline::new);
    }

    /**
     * Returns the type of this task.
     *
     * @return Type of this task as a string.
     */
    @Override
    String getTaskType() {
        return "D";
    }

    /**
     * Returns this task as a string to display to the user.
     *
     * @return This task as a string.
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + getTimeString() + ")";
    }
}
