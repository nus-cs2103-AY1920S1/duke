package duke.storage;

import duke.exception.IoDukeException;
import duke.task.Deadline;
import duke.util.DateFormatter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Manages serialization of Deadline.
 */
public class DeadlineStorageSerializer {
    /**
     * Serializes a Deadline object.
     *
     * @param deadline Deadline object
     * @return Storage string of the object
     */
    public static String serialize(Deadline deadline) {
        List<String> data = new ArrayList<>();

        data.add(deadline.getType());
        data.add(Boolean.toString(deadline.getIsDone()));
        data.add(deadline.getDescription());
        data.add(DateFormatter.format(deadline.getBy()));

        return String.join("|", data) + "\n";
    }

    /**
     * Deserializes a Deadline object.
     *
     * @param tokens Input tokens
     * @return Deadline task based on tokens
     */
    public static Deadline deserialize(String[] tokens) throws IoDukeException, ParseException {
        if (tokens.length != 4) {
            throw new IoDukeException("Invalid Deadline format");
        }

        boolean done = Boolean.parseBoolean(tokens[1]);
        String description = tokens[2];
        Date by = DateFormatter.parse(tokens[3]);

        return new Deadline(description, by, done);
    }
}
