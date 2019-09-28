package duke.task;

import duke.Storage;
import duke.command.Command;

public class Event extends TimeTask {
    Event(String description, String time) {
        super(description, time);
    }

    /**
     * Returns a Command which generates an Event given the input line.
     *
     * @param tasks The shared list of tasks.
     * @param storage Storage to save the tasks after adding the event.
     * @return A Command which generates events.
     */
    public static Command getCommand(TaskList tasks, Storage storage) {
        return getCommand(tasks, storage, "at", "an event", Event::new);
    }

    /**
     * Returns the type of this task.
     *
     * @return Type of this task as a string.
     */
    @Override
    String getTaskType() {
        return "E";
    }

    /**
     * Returns this task as a string to display to the user.
     *
     * @return This task as a string.
     */
    @Override
    public String toString() {
        return super.toString() + " (at: " + getTimeString() + ")";
    }
}
