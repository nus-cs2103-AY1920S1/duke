package task.tasks;

import error.UnknownCommandException;
import error.task.TaskCreationException;

import java.io.Serializable;

/***
 * <p>
 * Functional interface that returns a task.
 * Serializable to be written to storage.
 * </p>
 */
@FunctionalInterface
public interface SerializableTaskProducer extends Serializable {
    static final long serialVersionUID = 6529685098267157192L;
    public Task getTask(String arguments) throws TaskCreationException;
}
