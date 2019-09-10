package duke.task.tasks;

import duke.task.Task;
import duke.task.tasks.entities.TaskType;
import duke.task.tasks.entities.TimeFrame;
import error.task.TaskCreationException;

import java.time.LocalDateTime;

/***
 * <p>
 * Task that needs to be done at a specified time.
 * </p>
 */
public class Event extends Task {
    public Event(String description, LocalDateTime at, boolean isDone, boolean isRecurring) throws TaskCreationException {
        super(TaskType.EVENT, description, new TimeFrame(at, at), isDone, isRecurring);
    }
}
