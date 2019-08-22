package task.tasks;

import task.Task;

import java.util.function.Function;

public enum TaskKeyword {
    TODO("todo", (String arguments) -> new ToDo(arguments)),
    EVENT("event", (String arguments) -> new Event(arguments)),
    DEADLINE("deadline", (String arguments) -> new Deadline(arguments));

    public final String keyword;
    public final Function<String, Task> taskProducer;

    TaskKeyword(String keyword, Function<String, Task> taskProducer) {
        this.keyword = keyword;
        this.taskProducer = taskProducer;
    }
}
