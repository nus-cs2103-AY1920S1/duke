package duke.storage;

import duke.exception.IoDukeException;
import duke.task.Event;
import duke.util.DateFormatter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Manages serialization of Event.
 */
public class EventStorageSerializer {
    /**
     * Serializes an Event object.
     *
     * @param event Event object
     * @return Storage string of the object
     */
    public static String serialize(Event event) {
        List<String> data = new ArrayList<>();

        data.add(event.getType());
        data.add(Boolean.toString(event.getIsDone()));
        data.add(event.getDescription());
        data.add(DateFormatter.format(event.getAt()));

        return String.join("|", data) + "\n";
    }

    /**
     * Deserializes an Event object.
     *
     * @param tokens Input tokens
     * @return Event task based on tokens
     */
    public static Event deserialize(String[] tokens) throws IoDukeException, ParseException {
        if (tokens.length != 4) {
            throw new IoDukeException("Invalid Event format");
        }

        boolean done = Boolean.parseBoolean(tokens[1]);
        String description = tokens[2];
        Date at = DateFormatter.parse(tokens[3]);

        return new Event(description, at, done);
    }
}
