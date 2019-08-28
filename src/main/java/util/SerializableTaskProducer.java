package util;

import error.task.TaskCreationException;
import task.Task;

import java.io.Serializable;

@FunctionalInterface
public interface SerializableTaskProducer extends Serializable {
    public Task getTask(String arguments) throws TaskCreationException;
}
