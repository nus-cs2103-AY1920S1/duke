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
    TODO("todo", 'T', ToDo.class),
    EVENT("event", 'E', Event.class),
    DEADLINE("deadline", 'D', Deadline.class),
    DO_WITHIN("within", 'W', DoWithin.class),
    DO_AFTER("after", 'A', DoAfter.class);

    private static final long serialVersionUID = 6529685098267757688L;


    public final String keyword;
    public final Character code;
    public final Class<? extends Task> task;

    TaskType(String keyword, Character code, Class<? extends Task> task) {
        this.keyword = keyword;
        this.code = code;
        this.task = task;
    }
}
