package duke.task.tasks;

import duke.task.Task;
import duke.task.tasks.entities.TaskType;
import duke.task.tasks.entities.TimeFrame;
import error.task.TaskCreationException;

/***
 * <p>
 * Basic duke.task.
 * </p>
 */
public class ToDo extends Task {
    /***
     * <p>
     * ToDo constructor.
     * </p>
     */
    public ToDo(String details, boolean isDone, boolean isRecurring) {
        super(TaskType.TODO, details, new TimeFrame(null, null), isDone, isRecurring);
    }
}
