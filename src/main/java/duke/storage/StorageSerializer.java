package duke.storage;

import duke.exception.IoDukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import java.text.ParseException;

/**
 * Manages serialization of data.
 */
public class StorageSerializer {
    /**
     * Serializes tasks and returns a readable, storage version of itself.
     *
     * @param task Task to be serialized
     * @return String Serialized data of task
     * @throws IoDukeException If the task cannot be serialized
     */
    public static String serialize(Task task) throws IoDukeException {
        String serializedData;

        if (task instanceof Todo) {
            serializedData = TodoStorageSerializer.serialize((Todo)task);
        } else if (task instanceof Deadline) {
            serializedData = DeadlineStorageSerializer.serialize((Deadline)task);
        } else if (task instanceof Event) {
            serializedData = EventStorageSerializer.serialize((Event)task);
        } else {
            throw new IoDukeException("Attempted to save unsupported task type");
        }

        return serializedData;
    }

    /**
     * Deserializes a task based on input tokens.
     *
     * @param tokens Input tokens
     * @return Task
     * @throws ParseException If Date format parsing fails
     * @throws IoDukeException If an invalid task type was found
     */
    public static Task deserialize(String[] tokens) throws ParseException, IoDukeException {
        if (tokens.length < 1) {
            throw new IoDukeException("The task file is corrupted");
        }

        Task task;
        switch (tokens[0]) {
        case "T":
            task = TodoStorageSerializer.deserialize(tokens);
            break;
        case "D":
            task = DeadlineStorageSerializer.deserialize(tokens);
            break;
        case "E":
            task = EventStorageSerializer.deserialize(tokens);
            break;
        default:
            throw new IoDukeException("Invalid task type found");
        }

        return task;
    }
}
