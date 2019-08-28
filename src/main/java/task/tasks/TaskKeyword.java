package task.tasks;

import task.Task;
import util.SerializableTaskProducer;

import java.util.function.Function;

public enum TaskKeyword {
    TODO("todo", ToDo::new),
    EVENT("event", Event::new),
    DEADLINE("deadline", Deadline::new);

    public final String keyword;
    public final SerializableTaskProducer taskProducer;

    TaskKeyword(String keyword, SerializableTaskProducer taskProducer) {
        this.keyword = keyword;
        this.taskProducer = taskProducer;
    }
}
