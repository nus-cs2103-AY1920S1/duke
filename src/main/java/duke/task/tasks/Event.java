package duke.task.tasks;

import duke.task.Task;
import duke.task.tasks.entities.TaskType;
import duke.task.tasks.entities.TimeFrame;
import error.task.TaskCreationException;

import java.time.LocalDateTime;

/**
 * Task that needs to be done at a specified time.
 */
public class Event extends Task {
    public Event(String details, LocalDateTime at, Boolean isDone, Boolean isRecurring) {
        super(TaskType.EVENT, details, new TimeFrame(at, at), isDone, isRecurring);
    }
}
