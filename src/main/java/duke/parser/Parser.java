package duke.parser;

import duke.DukeException;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.todo.Deadline;
import duke.todo.Event;
import duke.todo.Task;
import duke.todo.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    public static final String DATE_FORMAT = "dd/MM/yyyy HHmm";
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
    public static final String ERROR_INVALID_INPUT = "OOPS!!! I'm sorry, but I don't know what that means :-(";

    // Private constructor to prevent illegal instantiation of Parser class
    private Parser() throws DukeException {
        throw new DukeException("Unauthorized invocation");
    }

    /**
     * Parses the input string and generates Command object
     * based on the input task type and relevant details.
     * Throws DukeException when the input does not fit into
     * any type.
     *
     * @param input Input string.
     * @return Command generated.
     * @throws DukeException When input does not fit any types.
     */
    public static Command parse(String input) throws DukeException {
        String taskType = input.split(" ")[0];
        int index;
        String trimmedInput;

        switch (taskType) {
        case "bye":
            return new ByeCommand();
        case "done":
            index = Integer.parseInt(input.split(" ")[1]);
            return new DoneCommand(index);
        case "list":
            return new ListCommand();
        case "delete":
            index = Integer.parseInt(input.split(" ")[1]);
            return new DeleteCommand(index);
        case "todo":
            return handleTodo(input);
        case "deadline":
            trimmedInput = input.substring("deadline ".length());
            return new DeadlineCommand(parseUserEntryDescription(trimmedInput), parseUserEntryDate(trimmedInput));
        case "event":
            trimmedInput = input.substring("event ".length());
            return new EventCommand(parseUserEntryDescription(trimmedInput), parseUserEntryDate(trimmedInput));
        case "find":
            return new FindCommand(input.substring("find ".length()));
        default:
            throw new DukeException(ERROR_INVALID_INPUT);
        }

    }

    private static Command handleTodo(String input) {
        String trimmedInput;
        trimmedInput = input.substring("todo ".length());

        if (trimmedInput.contains("(needs")) {
            String description = trimmedInput.split("\\(needs")[0].trim();
            String duration = trimmedInput.split("\\(needs")[1].split("\\)")[0].trim();
            System.out.println(duration);
            return new TodoCommand(description, duration);
        }
        return new TodoCommand(trimmedInput);
    }

    /**
     * Parses the user input and returns the task description.
     *
     * @param input User input.
     * @return Task description.
     */
    private static String parseUserEntryDescription(String input) {
        return input.split("/by|/at")[0];
    }

    /**
     * Parses the user input and returns the task date.
     *
     * @param input User input.
     * @return Task date.
     */
    private static LocalDateTime parseUserEntryDate(String input) {
        String rawDate = input.split("/by|/at")[1].trim();
        return LocalDateTime.parse(rawDate, formatter);
    }

    public static String parseFileEntryTaskType(String fileEntry) {
        return fileEntry.split("\\|")[0].trim();
    }

    /**
     * Parses the file entry and returns the task description.
     *
     * @param fileEntry File entry.
     * @return Task description.
     */
    public static String parseFileEntryTaskDescription(String fileEntry) {
        return fileEntry.split("\\|")[1].trim();
    }

    /**
     * Parses the file entry and returns the task date.
     *
     * @param fileEntry File entry.
     * @return Task date.
     */
    public static LocalDateTime parseFileEntryTaskDate(String fileEntry) {
        String rawDate = fileEntry.split("\\|")[2].trim();
        return LocalDateTime.parse(rawDate, formatter);
    }

    /**
     * Parse the file entry and returns the task contained.
     *
     * @param fileEntry File entry.
     * @return Task contained in the entry.
     * @throws DukeException When the entry is corrupted.
     */
    public static Task parseTaskFromFile(String fileEntry) throws DukeException {
        String taskType = Parser.parseFileEntryTaskType(fileEntry);
        String taskDescription = parseFileEntryTaskDescription(fileEntry);

        switch (taskType) {
        case "T":
            return new Todo(taskDescription);
        case "D":
            LocalDateTime deadline = Parser.parseFileEntryTaskDate(fileEntry);
            return new Deadline(taskDescription, deadline);
        case "E":
            LocalDateTime eventDate = Parser.parseFileEntryTaskDate(fileEntry);
            return new Event(taskDescription, eventDate);
        default:
            throw new DukeException(ERROR_INVALID_INPUT);
        }
    }
}
