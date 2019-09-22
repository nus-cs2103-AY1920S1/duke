package duke.task.creation;

import duke.task.Task;
import duke.task.tasks.DoAfter;
import duke.task.tasks.Deadline;
import duke.task.tasks.DoWithin;
import duke.task.tasks.Event;
import duke.task.tasks.ToDo;

import java.io.Serializable;

/**
 * Enum used by the TaskFactory to generate the corresponding tasks. A task MUST be registered in this enum before
 * it will be produced by the TaskFactory. Each task is mapped to a keyword in the enum. This keyword is used
 * by the TaskFactory to determine what type of task to create.
 */
public enum TaskType {
    TODO("todo",  0, ToDo.class),
    EVENT("event",  1, Event.class),
    DEADLINE("deadline",  1, Deadline.class),
    DO_WITHIN("within",  2, DoWithin.class),
    DO_AFTER("after",  1, DoAfter.class);

    public final String keyword;
    public final int numDates;
    public final Class<? extends Task> task;

    TaskType(String keyword, int numDates, Class<? extends Task> task) {
        this.keyword = keyword;
        this.numDates = numDates;
        this.task = task;
    }
}
