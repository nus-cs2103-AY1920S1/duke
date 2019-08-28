package task.tasks;

import java.io.Serializable;

/***
 * <p>
 * Enum used to encapsulate a task's keyword to be input by the user to perform related commands.
 * Each Task should have its own corresponding TaskKeyword which has to be passed into Task superclass's constructor.
 * TaskKeyword should contain a SerializableTaskProducer that is used to procure its corresponding class.
 * Serializable to be written to storage.
 * </p>
 */
public enum TaskKeyword implements Serializable {
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
