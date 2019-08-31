import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private static final DateTimeFormatter standardFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    public static Command parseCommand(String commandString) throws DukeException {
        String[] commandArr = commandString.split("\\s+", 2);
        CommandEnum commandEnum;
        try {
            commandEnum = CommandEnum.valueOf(commandArr[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DukeException("☹ OOPS! I can't do it!");
        }
        switch (commandEnum) {
        case BYE:
            return new ByeCommand();
        case LIST:
            return new ListCommand();
        case DONE:
            if (commandArr.length <= 1) {
                throw new DukeException("☹ OOPS! Task number missing!");
            }
            return new DoneCommand(parseTaskNumber(commandArr[1]));
        case DELETE:
            if (commandArr.length <= 1) {
                throw new DukeException("☹ OOPS! Task number missing!");
            }
            return new DeleteCommand(parseTaskNumber(commandArr[1]));
        case TODO:
            if (commandArr.length <= 1) {
                throw new DukeException("☹ OOPS! Todo description missing!");
            }
            return new TodoCommand(commandArr[1]);
        case DEADLINE:
            if (commandArr.length <= 1) {
                throw new DukeException("☹ OOPS! Deadline description missing!");
            }
            String[] deadlineInputArr = parseDeadline(commandArr[1]);
            return new DeadlineCommand(deadlineInputArr[0], deadlineInputArr[1]);
        case EVENT:
            if (commandArr.length <= 1) {
                throw new DukeException("☹ OOPS! Deadline description missing!");
            }
            String[] eventInputArr = parseEvent(commandArr[1]);
            return new EventCommand(eventInputArr[0], eventInputArr[1]);
        default:
            throw new DukeException("☹ OOPS! I can't do it!");
        }
    }

    public static LocalDateTime parseDateTime(String dateTimeString) throws DukeException {
        try {
            return LocalDateTime.parse(dateTimeString, standardFormat);
        } catch (DateTimeParseException e) {
            throw new DukeException("☹ OOPS! Date Time format invalid!");
        }
    }

    private static String[] parseDeadline(String deadlineString) throws DukeException {
        String[] deadlineArr = deadlineString.split(" /by ");
        if (deadlineArr.length <= 1) {
            if (deadlineString.indexOf("/by") == 0) {
                throw new DukeException("☹ OOPS! Deadline description format invalid!");
            } else {
                throw new DukeException("☹ OOPS! Deadline due date missing!");
            }
        }
        return deadlineArr;
    }

    private static String[] parseEvent(String eventString) throws DukeException {
        String[] eventArr = eventString.split(" /at ");
        if (eventArr.length <= 1) {
            if (eventString.indexOf("/at") == 0) {
                throw new DukeException("☹ OOPS! Event description format invalid!");
            } else {
                throw new DukeException("☹ OOPS! Event timing missing!");
            }
        }
        return eventArr;
    }

    private static int parseTaskNumber(String intString) throws DukeException {
        int result;
        try {
            result = Integer.parseInt(intString);
        } catch (NumberFormatException e) {
            throw new DukeException("☹ OOPS! Task number format invalid!");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS! Task number invalid!");
        }
        return result;
    }
}
