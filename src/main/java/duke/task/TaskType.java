package duke.task;

import duke.task.tasks.Deadline;
import duke.task.tasks.Event;
import duke.task.tasks.ToDo;

public enum TaskType {
    TODO("todo", 'T', ToDo.class),
    EVENT("event", 'E', Event.class),
    DEADLINE("deadline", 'D', Deadline.class);

    public final String keyword;
    public final Character code;
    public final Class<? extends Task> task;

    TaskType(String keyword, Character code, Class<? extends Task> task) {
        this.keyword = keyword;
        this.code = code;
        this.task = task;
    }
}
