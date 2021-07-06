package duke.storage;

import duke.date.InvalidDateDukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.InvalidTaskDukeException;
import duke.task.Task;
import duke.task.Todo;

/**
 * Serializes and de-serializes tasks to load and save them onto the hard-disk.
 */
public class Serializer {

    private static final String DESERIALIZE_FAIL_MSG = "Unable to deserialize task from text.";
    private static final String SERIALIZE_FAIL_MSG = "Oops! Unable to serialize this task.";

    /**
     * De-serializes a task entry from the text file on the hard disk and returns the corresponding task object.
     * @param input String task entry
     * @return Task object
     * @throws InvalidTaskDukeException If task is invalid.
     * @throws InvalidDateDukeException If date is invalid.
     */
    public Task deserializeTask(String input) throws InvalidTaskDukeException, InvalidDateDukeException {
        if (input.isBlank()) {
            throw new InvalidTaskDukeException(DESERIALIZE_FAIL_MSG);
        }
        String[] tokens = tokenize(input);
        if (isTodo(tokens)) {
            return deserializeTodo(tokens);
        } else if (isDeadline(tokens)) {
            return deserializeDeadline(tokens);
        } else if (isEvent(tokens)) {
            return deserializeEvent(tokens);
        } else {
            throw new InvalidTaskDukeException(DESERIALIZE_FAIL_MSG);
        }
    }

    private boolean isTodo(String[] tokens) {
        return tokens[0].equals("T");
    }

    private boolean isDeadline(String[] tokens) {
        return tokens[0].equals("D");
    }

    private boolean isEvent(String[] tokens) {
        return tokens[0].equals("E");
    }

    private String[] tokenize(String input) {
        return input.split(" \\| ");
    }

    private Task deserializeTodo(String[] tokens) throws InvalidTaskDukeException {
        Task t = new Todo(tokens[2]);
        if (tokens[1].equals("1")) {
            t.setDone(true);
        }
        return t;
    }

    private Task deserializeEvent(String[] tokens) throws InvalidDateDukeException, InvalidTaskDukeException {
        Task t = new Event(tokens[2], tokens[3]);
        if (tokens[1].equals("1")) {
            t.setDone(true);
        }
        return t;
    }

    private Task deserializeDeadline(String[] tokens) throws InvalidDateDukeException, InvalidTaskDukeException {
        Task t = new Deadline(tokens[2], tokens[3]);
        if (tokens[1].equals("1")) {
            t.setDone(true);
        }
        return t;
    }

    /**
     * Serialize task to store it into a text file on the hard-disk.
     * @param task Task object
     * @return String representing a task entry.
     * @throws InvalidTaskDukeException If the task is invalid.
     */
    public String serializeTask(Task task) throws InvalidTaskDukeException {
        String completionState = getCompletionState(task);
        String description = task.getDescription();
        if (task instanceof Todo) {
            return serializeTodo(completionState, description);
        } else if (task instanceof Event) {
            return serializeEvent(completionState, description, task);
        } else if (task instanceof Deadline) {
            return serializeDeadline(completionState, description, task);
        } else {
            throw new InvalidTaskDukeException(SERIALIZE_FAIL_MSG);
        }
    }

    private String getCompletionState(Task task) {
        return task.getIsDone() ? "1" : "0";
    }

    private String serializeTodo(String completionState, String description) {
        return "T | " + completionState + " | " + description;
    }

    private String serializeEvent(String completionState, String description, Task task) {
        return "E | " + completionState + " | " + description + " | " + task.getTime();
    }

    private String serializeDeadline(String completionState, String description, Task task) {
        return "D | " + completionState + " | " + description + " | " + task.getTime();
    }
}
