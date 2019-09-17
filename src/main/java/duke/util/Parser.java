package duke.util;

import duke.exception.DukeException;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.CommandEnum;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TagCommand;
import duke.command.TodoCommand;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parser class, to handle various parsing in Duke application.
 */
public class Parser {
    private static final DateTimeFormatter STANDARD_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Converts the input string to a Command object.
     *
     * @param commandString Input string representing a command.
     * @return a Command object corresponding to the input string.
     * @throws DukeException if the command is invalid.
     */
    public static Command parseCommand(String commandString) throws DukeException {
        String[] commandArr = commandString.split("\\s+", 2);
        CommandEnum commandEnum;
        try {
            commandEnum = CommandEnum.valueOf(commandArr[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DukeException("I can't do it!");
        }
        switch (commandEnum) {
        case BYE:
            return new ByeCommand();
        case LIST:
            return new ListCommand();
        case FIND:
            if (commandArr.length <= 1) {
                throw new DukeException("Keyword missing!");
            }
            return new FindCommand(commandArr[1]);
        case DONE:
            if (commandArr.length <= 1) {
                throw new DukeException("Task number missing!");
            }
            return new DoneCommand(parseTaskNumber(commandArr[1]));
        case DELETE:
            if (commandArr.length <= 1) {
                throw new DukeException("Task number missing!");
            }
            return new DeleteCommand(parseTaskNumber(commandArr[1]));
        case TAG:
            if (commandArr.length <= 1) {
                throw new DukeException("Tag description missing!");
            }
            String[] tagInputArr = parseTag(commandArr[1]);
            return new TagCommand(parseTaskNumber(tagInputArr[0]), tagInputArr[1]);
        case TODO:
            if (commandArr.length <= 1) {
                throw new DukeException("Todo description missing!");
            }
            return new TodoCommand(commandArr[1]);
        case DEADLINE:
            if (commandArr.length <= 1) {
                throw new DukeException("Deadline description missing!");
            }
            String[] deadlineInputArr = parseDeadline(commandArr[1]);
            return new DeadlineCommand(deadlineInputArr[0], deadlineInputArr[1]);
        case EVENT:
            if (commandArr.length <= 1) {
                throw new DukeException("Event description missing!");
            }
            String[] eventInputArr = parseEvent(commandArr[1]);
            return new EventCommand(eventInputArr[0], eventInputArr[1]);
        default:
            throw new DukeException("I can't do it!");
        }
    }

    /**
     * Converts the input string to a DateTime object.
     *
     * @param dateTimeString Input string representing a datetime.
     * @return a DateTime object corresponding to the input string.
     * @throws DukeException if the datetime is invalid.
     */
    public static LocalDateTime parseDateTime(String dateTimeString) throws DukeException {
        try {
            return LocalDateTime.parse(dateTimeString, STANDARD_FORMAT);
        } catch (DateTimeParseException e) {
            throw new DukeException("Date Time format invalid!");
        }
    }

    /**
     * Parses the input string to an array of useful information.
     *
     * @param deadlineString Input string representing a deadline.
     * @return an array of useful information corresponding to the input string.
     * @throws DukeException if the string format is invalid.
     */
    private static String[] parseDeadline(String deadlineString) throws DukeException {
        String[] deadlineArr = deadlineString.split(" /by ");
        if (deadlineArr.length <= 1) {
            if (deadlineString.indexOf("/by") == 0) {
                throw new DukeException("Deadline description format invalid!");
            } else {
                throw new DukeException("Deadline due date missing!");
            }
        }
        return deadlineArr;
    }

    /**
     * Parses the input string to an array of useful information.
     *
     * @param eventString Input string representing a event.
     * @return an array of useful information corresponding to the input string.
     * @throws DukeException if the string format is invalid.
     */
    private static String[] parseEvent(String eventString) throws DukeException {
        String[] eventArr = eventString.split(" /at ");
        if (eventArr.length <= 1) {
            if (eventString.indexOf("/at") == 0) {
                throw new DukeException("Event description format invalid!");
            } else {
                throw new DukeException("Event timing missing!");
            }
        }
        return eventArr;
    }

    /**
     * Parses the input string to an array of useful information.
     *
     * @param tagString Input string representing a tag.
     * @return an array of useful information corresponding to the input string.
     * @throws DukeException if the string format is invalid.
     */
    private static String[] parseTag(String tagString) throws DukeException {
        String[] tagArr = tagString.split(" /as ");
        if (tagArr.length <= 1) {
            if (tagString.indexOf("/as") == 0) {
                throw new DukeException("Tag description format invalid!");
            } else {
                throw new DukeException("Tag name missing!");
            }
        }
        return tagArr;
    }

    /**
     * Parses the input string to an array of useful information.
     *
     * @param intString Input string representing a task number.
     * @return a task number corresponding to the input string.
     * @throws DukeException if the task number format is invalid.
     */
    private static int parseTaskNumber(String intString) throws DukeException {
        int result;
        try {
            result = Integer.parseInt(intString);
        } catch (NumberFormatException e) {
            throw new DukeException("Task number format invalid!");
        }
        return result;
    }
}
