import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private static final DateTimeFormatter standardFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    public static String[] parseCommand(String commandString) {
        return commandString.split("\\s+", 2);
    }

    public static LocalDateTime parseDateTime(String dateTimeString) throws DukeException {
        try {
            return LocalDateTime.parse(dateTimeString, standardFormat);
        } catch (DateTimeParseException e) {
            throw new DukeException("☹ OOPS! Date Time format invalid!");
        }
    }

    public static String[] parseDeadline(String deadlineString) throws DukeException {
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

    public static String[] parseEvent(String eventString) throws DukeException {
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

    public static int parseInt(String intString) throws DukeException {
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
