package duke.task.tasks.entities;

import duke.task.Task;
import duke.task.tasks.DoAfter;
import duke.task.tasks.Deadline;
import duke.task.tasks.DoWithin;
import duke.task.tasks.Event;
import duke.task.tasks.ToDo;

import java.io.Serializable;

/**
 * Enum encapsulating different user tasks and its character code.
 */
public enum TaskType implements Serializable {
    TODO("todo", 'T', 0, ToDo.class),
    EVENT("event", 'E', 1, Event.class),
    DEADLINE("deadline", 'D', 1, Deadline.class),
    DO_WITHIN("within", 'W', 2, DoWithin.class),
    DO_AFTER("after", 'A', 1, DoAfter.class);

    private static final long serialVersionUID = 6529685098267757688L;


    public final String keyword;
    public final Character code;
    public final int numDates;
    public final Class<? extends Task> task;

    TaskType(String keyword, Character code, int numDates, Class<? extends Task> task) {
        this.keyword = keyword;
        this.code = code;
        this.numDates = numDates;
        this.task = task;
    }
}
