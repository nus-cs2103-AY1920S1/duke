package task.tasks;

import task.Task;

import java.util.function.Function;

public enum TaskKeyword {
    TODO("todo", ToDo::new),
    EVENT("event", Event::new),
    DEADLINE("deadline", Deadline::new);

    public final String keyword;
    public final Function<String, Task> taskProducer;

    TaskKeyword(String keyword, Function<String, Task> taskProducer) {
        this.keyword = keyword;
        this.taskProducer = taskProducer;
    }
}
