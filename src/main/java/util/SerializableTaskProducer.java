package util;

import error.UnknownCommandException;
import error.task.TaskCreationException;
import task.Task;

import java.io.Serializable;

@FunctionalInterface
public interface SerializableTaskProducer extends Serializable {
    static final long serialVersionUID = 6529685098267157192L;
    public Task getTask(String arguments) throws TaskCreationException;
}
