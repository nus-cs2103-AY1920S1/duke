import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringJoiner;

public class Storage {
    public static final String FIELD_DELIMITER = "\u001F";
    private static final Map<String, Class<? extends Task>> EVENT_TOKEN_TO_TYPE = Map.of(
            "T", Todo.class,
            "D", Deadline.class,
            "E", Event.class
    );
    private static final Map<Class<? extends Task>, String> EVENT_TYPE_TO_TOKEN;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    static {
        // creates an immutable reverse mapping of EVENT_TOKEN_TO_TYPE and stores it as EVENT_TYPE_TO_TOKEN
        Map<Class<? extends Task>, String> builderMap = new HashMap<>(EVENT_TOKEN_TO_TYPE.size());
        for (Map.Entry<String, Class<? extends Task>> kvp : EVENT_TOKEN_TO_TYPE.entrySet()) {
            builderMap.put(kvp.getValue(), kvp.getKey());
        }

        EVENT_TYPE_TO_TOKEN = Collections.unmodifiableMap(builderMap);
    }

    private final Path filePath;

    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() {
        return parseFromFile(filePath);
    }

    public void save(List<Task> tasks) {
        serializeToFile(filePath, tasks);
    }

    /**
     * Convenience function that loads the given file path and parses the Tasks within, returning a new List of the Tasks.
     *
     * @param file File path that contains the Tasks to load.
     * @return A new List of the Tasks represented by the given file path.
     */
    public static List<Task> parseFromFile(Path file) {
        return parseFromFile(file, new ArrayList<>());
    }

    /**
     * Loads the given file path and parses the Tasks within, adding to the provided List of the Tasks.
     *
     * @param file  File path that contains the Tasks to load.
     * @param tasks Pre-existing List of Tasks to be append to.
     * @return The List of Tasks appended with the new Tasks represented by the given file path.
     */
    public static List<Task> parseFromFile(Path file, List<Task> tasks) {
        try (final Reader reader = Files.newBufferedReader(file)) {
            return parse(reader, tasks);
        } catch (IOException exc) {
            throw new FileIOException(String.format("Unable to read tasks from file: %s", file.toString()), exc);
        }
    }

    /**
     * Convenience function that parses the Tasks from the input, returning a new List of the Tasks.
     *
     * @param input Stream of characters to parse Tasks from.
     * @return A new List of the Tasks represented by the given input.
     */
    public static List<Task> parse(Reader input) {
        return parse(input, new ArrayList<>());
    }

    /**
     * Parse the Tasks from the input, adding to the provided List of the Tasks.
     *
     * @param input Stream of characters to parse Tasks from.
     * @param tasks Pre-existing List of Tasks to be append to.
     * @return The List of Tasks appended with the new Tasks represented by the given input.
     */
    public static List<Task> parse(Reader input, List<Task> tasks) {
        try (final Scanner sc = new Scanner(input)) {
            while (sc.hasNextLine()) {
                Task task = parseLine(sc.nextLine());

                assert task != null;
                tasks.add(task);
            }

            return tasks;
        }
    }

    /**
     * Parses a single line of text to instantiate a Task subclass instance.
     *
     * @param line Textual representation of a Task instance.
     * @return Task subclass instance (either Todo, Deadline or Event)
     */
    private static Task parseLine(String line) {
        final String[] tokens = line.split(FIELD_DELIMITER);

        final Class<? extends Task> taskType = parseTypeToken(tokens[0]);
        final boolean isTaskDone = parseDoneToken(tokens[1]);
        final String taskDescription = tokens[2];

        Task task = null;
        if (taskType == Todo.class) {
            task = new Todo(taskDescription, isTaskDone);
        } else if (taskType == Deadline.class) {
            if (tokens.length != 4) {
                throw new MissingTokenParseError(String.format("Deadline task expected 4 values, received %d instead.", tokens.length));
            }

            final LocalDateTime deadline = parseDateTime(tokens[3]);
            task = new Deadline(taskDescription, isTaskDone, deadline);
        } else if (taskType == Event.class) {
            if (tokens.length != 4) {
                throw new MissingTokenParseError(String.format("Event task expected 4 values, received %d instead.", tokens.length));
            }

            final LocalDateTime dateTime = parseDateTime(tokens[3]);
            task = new Event(taskDescription, isTaskDone, dateTime);
        }

        return task;
    }

    /**
     * Parses the textual representation of a Task subtype (e.g. "T" returns Todo.class).
     *
     * @param typeToken The textual representation of a subtype extending from Task.
     * @return The subtype extending from Task.
     */
    private static Class<? extends Task> parseTypeToken(String typeToken) {
        Class<? extends Task> taskType = EVENT_TOKEN_TO_TYPE.get(typeToken);
        if (taskType == null) {
            throw new TypeTokenParseError(String.format("\"%s\" is not a valid task type value.", typeToken));
        } else {
            return taskType;
        }
    }

    /**
     * Parses the textual representation whether a Task is marked as done.
     *
     * @param doneToken Expected either "0" for not done or "1" for done.
     * @return Boolean for whether a Task is marked as done.
     */
    private static boolean parseDoneToken(String doneToken) {
        switch (doneToken) {
        case "1":
            return true;
        // no need for break here, return implicitly does the same
        case "0":
            return false;
        // no need for break here, return implicitly does the same
        default:
            throw new DoneTokenParseError(String.format("\"%s\" is not a valid done value.", doneToken));
        }
    }

    private static LocalDateTime parseDateTime(String dateTimeToken) {
        return LocalDateTime.parse(dateTimeToken, DATE_TIME_FORMATTER);
    }

    /**
     * Serializes a list of Task instances to a textual representation and writes it to the provided file path.
     *
     * @param file  File path to write the Tasks to.
     * @param tasks List of Tasks to store in the file.
     */
    public static void serializeToFile(Path file, Iterable<Task> tasks) {
        try (final Writer output = Files.newBufferedWriter(file)) {
            output.write(serialize(tasks));
        } catch (IOException exc) {
            throw new FileIOException(String.format("Unable to save tasks to file: %s", file.toString()), exc);
        }
    }

    /**
     * Serializes a list of Task instances to a textual representation.
     *
     * @param tasks List of Tasks to convert to a textual representation.
     * @return The textual representation for the given list of Tasks.
     */
    public static String serialize(Iterable<Task> tasks) {
        final StringJoiner output = new StringJoiner("\n");
        for (Task t : tasks) {
            output.add(serializeTask(t));
        }
        return output.toString();
    }

    /**
     * Serializes a Task instance to its textual representation.
     *
     * @param t Task to serialize to text.
     * @return The textual representation for the given Task.
     */
    private static String serializeTask(Task t) {
        final StringJoiner output = new StringJoiner(FIELD_DELIMITER);

        final String taskType = serializeTypeToToken(t);
        final String isTaskDone = serializeDoneToToken(t);
        final String taskDescription = t.getDescription();

        output.add(taskType).add(isTaskDone).add(taskDescription);

        if (t instanceof Deadline) {
            Deadline d = (Deadline) t;
            output.add(serializeDateTimeToToken(d.getDeadline()));
        } else if (t instanceof Event) {
            Event e = (Event) t;
            output.add(serializeDateTimeToToken(e.getEventDateTime()));
        }

        return output.toString();
    }

    /**
     * Serializes the subtype of a Task instance to its textual representation (e.g. an Event instance returns "E").
     *
     * @param t Task to serialize its subtype.
     * @return The textual representation of a Task's subtype.
     */
    private static String serializeTypeToToken(Task t) {
        String taskType = EVENT_TYPE_TO_TOKEN.get(t.getClass());
        if (taskType == null) {
            throw new UnserializableType(String.format("\"%s\" is not a serializable task type.", t.getClass().getName()));
        } else {
            return taskType;
        }
    }

    /**
     * Serializes whether a Task instance is marked as done (i.e. done returns "1", not done returns "0").
     *
     * @param t Task to serialize its done state.
     * @return The textual representation of the Task's done state.
     */
    private static String serializeDoneToToken(Task t) {
        return t.isDone() ? "1" : "0";
    }

    private static String serializeDateTimeToToken(LocalDateTime dateTime) {
        return dateTime.format(DATE_TIME_FORMATTER);
    }
}

abstract class SerializerException extends DukeException {
    public SerializerException(String message) {
        super(message);
    }

    public SerializerException(String message, Throwable cause) {
        super(message, cause);
    }
}

class FileIOException extends SerializerException {
    public FileIOException(String message, IOException exc) {
        super(message, exc);
    }
}

abstract class TokenParseError extends SerializerException {
    public TokenParseError(String message) {
        super(message);
    }
}

class DoneTokenParseError extends TokenParseError {
    public DoneTokenParseError(String message) {
        super(message);
    }
}

class TypeTokenParseError extends TokenParseError {
    public TypeTokenParseError(String message) {
        super(message);
    }
}

class MissingTokenParseError extends TokenParseError {
    public MissingTokenParseError(String message) {
        super(message);
    }
}

abstract class SerializationError extends SerializerException {
    public SerializationError(String message) {
        super(message);
    }
}

class UnserializableType extends SerializationError {
    public UnserializableType(String message) {
        super(message);
    }
}
