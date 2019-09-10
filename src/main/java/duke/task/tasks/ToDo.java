package duke.task.tasks;

import duke.task.Task;
import duke.task.TaskType;
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
    public ToDo(String description, boolean isDone, boolean isRecurring) throws TaskCreationException {
        super(TaskType.TODO, description, new TimeFrame(null, null), isDone, isRecurring);
    }
}
