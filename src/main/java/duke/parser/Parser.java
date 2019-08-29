package duke.parser;

import duke.DukeException;
import duke.command.*;
import duke.common.Message;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Parses user input.
 */
public class Parser {

    /**
     * Parses user input into command for execution.
     *
     * @param inputLine entire text input entered by the user.
     * @return the command based on user input.
     * @throws DukeException if invalid command is entered by the user.
     */
    public static Command parse(String inputLine) throws DukeException {
        String command = getCommandFrom(inputLine);
        switch (command) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "done":
                return new DoneCommand(getIndexFrom(inputLine));
            case "todo":
                return new AddCommand(createTodoFrom(inputLine));
            case "deadline":
                return new AddCommand(createDeadlineFrom(inputLine));
            case "event":
                return new AddCommand(createEventFrom(inputLine));
            case "delete":
                return new DeleteCommand(getIndexFrom(inputLine));
            case "find":
                return new FindCommand(getKeywordFrom(inputLine));
            default:
                throw new DukeException(Message.MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }

    private static String getCommandFrom(String inputLine) {
        return inputLine.strip().split(" ")[0];
    }

    private static int getIndexFrom(String inputLine) throws DukeException {
        try {
            return Integer.parseInt(inputLine.strip().split(" ")[1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException(Message.MESSAGE_INVALID_TASK_INDEX);
        }
    }

    private static Todo createTodoFrom(String inputLine) throws DukeException {
        String todoDescription = inputLine.substring("todo".length()).strip();
        if (todoDescription.isEmpty()) {
            throw new DukeException(Message.MESSAGE_INVALID_TODO_FORMAT);
        }
        return new Todo(todoDescription);
    }

    private static Deadline createDeadlineFrom(String inputLine) throws DukeException {
        try {
            String[] deadlinePart = inputLine.substring("deadline".length()).split("/by");
            String deadlineDescription = deadlinePart[0].strip();
            if (deadlineDescription.isEmpty()) {
                throw new DukeException(Message.MESSAGE_INVALID_DEADLINE_FORMAT);
            }
            LocalDateTime by = LocalDateTime.parse(deadlinePart[1].strip(),
                    DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            return new Deadline(deadlineDescription, by);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(Message.MESSAGE_INVALID_DEADLINE_FORMAT);
        } catch (java.time.format.DateTimeParseException e) {
            throw new DukeException((Message.MESSAGE_INVALID_DATE_FORMAT));
        }
    }

    private static Event createEventFrom(String inputLine) throws DukeException {
        try {
            String[] eventPart = inputLine.substring("event".length()).split("/at");
            String eventDescription = eventPart[0].strip();
            if (eventDescription.isEmpty()) {
                throw new DukeException(Message.MESSAGE_INVALID_EVENT_FORMAT);
            }
            LocalDateTime at = LocalDateTime.parse(eventPart[1].strip(),
                    DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            return new Event(eventDescription, at);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(Message.MESSAGE_INVALID_EVENT_FORMAT);
        } catch (java.time.format.DateTimeParseException e) {
            throw new DukeException((Message.MESSAGE_INVALID_DATE_FORMAT));
        }
    }

    private static String getKeywordFrom(String inputLine) throws DukeException {
        String keyword = inputLine.substring("find".length()).strip();
        if (keyword.isEmpty()) {
            throw new DukeException(Message.MESSAGE_INVALID_KEYWORD_FORMAT);
        }
        return keyword;
    }
}
