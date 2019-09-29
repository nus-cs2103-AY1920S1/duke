package czkay.duke.model.task;

import czkay.duke.exception.DukeException;

/**
 * Represents the event task given by the user.
 */
public class Event extends TimedTask {

    public Event(String taskDescription, String timestamp) throws DukeException {
        super(taskDescription, timestamp);
    }

    /**
     * Formats the task such that it can be outputted in a readable form for the user.
     *
     * @return The description of the task.
     */
    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", (isDone ? "\u2713" : "\u2717"),
                super.taskDescription, super.timestamp.format(TimedTask.dateTimeFormatter));
    }

}
