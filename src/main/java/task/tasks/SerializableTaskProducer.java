package task.tasks;

import error.task.TaskCreationException;

import java.io.Serializable;

/***
 * Functional interface that returns a task.
 * Serializable to be written to storage.
 */
@FunctionalInterface
public interface SerializableTaskProducer extends Serializable {
    public Task getTask(String arguments) throws TaskCreationException;
}
