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
    public Deadline(String details, LocalDateTime by, Boolean isDone, Boolean isRecurring) {
        super(TaskType.DEADLINE, details, new TimeFrame(null, by), isDone, isRecurring);
    }
}
