package duke.task.tasks;

import duke.task.Task;
import duke.task.tasks.entities.TaskType;
import duke.task.tasks.entities.TimeFrame;
import error.task.TaskCreationException;

import java.time.LocalDateTime;

/***
 * <p>
 * Task that needs to be completed by a specified date.
 * </p>
 */
public class Deadline extends Task {
    public Deadline(String description, LocalDateTime by, boolean isDone, boolean isRecurring) throws TaskCreationException {
        super(TaskType.DEADLINE, description, new TimeFrame(null, by), isDone, isRecurring);
    }
}
