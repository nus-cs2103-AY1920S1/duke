package duke.storage;

import duke.exception.IoDukeException;
import duke.task.Todo;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages serialization of Todo.
 */
public class TodoStorageSerializer {
    /**
     * Serializes a Todo object.
     *
     * @param todo Todo object
     * @return Storage string of the object
     */
    public static String serialize(Todo todo) {
        List<String> data = new ArrayList<>();

        data.add(todo.getType());
        data.add(Boolean.toString(todo.getIsDone()));
        data.add(todo.getDescription());

        return String.join("|", data) + "\n";
    }

    /**
     * Deserializes a Todo object.
     *
     * @param tokens Input tokens
     * @return Todo task based on tokens
     */
    public static Todo deserialize(String[] tokens) throws IoDukeException {
        if (tokens.length != 3) {
            throw new IoDukeException("Invalid Todo format");
        }

        boolean done = Boolean.parseBoolean(tokens[1]);
        String description = tokens[2];

        return new Todo(description, done);
    }
}
