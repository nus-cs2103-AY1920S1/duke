package task.tasks;

import task.Task;
import util.SerializableFunction;

import java.io.Serializable;

public enum TaskKeyword implements Serializable {
    TODO("todo", ToDo::new),
    EVENT("event", Event::new),
    DEADLINE("deadline", Deadline::new);

    public final String keyword;
    public final SerializableFunction<String, Task> taskProducer;

    TaskKeyword(String keyword, SerializableFunction<String, Task> taskProducer) {
        this.keyword = keyword;
        this.taskProducer = taskProducer;
    }
}
