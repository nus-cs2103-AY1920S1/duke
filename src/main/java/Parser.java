import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Handles the data validation of user commands.
 */
public class Parser {

    public static Command parse(String input) throws DukeException {
        String[] inputArr = input.split(" ", 2);

        //Check what type of command is it
        String cmd = inputArr[0];
        Command nextCommand = new ExitCommand(cmd);

        if (cmd.equals("list")) {
            nextCommand = new ShowCommand(cmd);
        } else if (cmd.equals("todo")) {
            String desc = inputArr[1].trim();
            nextCommand = new AddCommand(cmd, desc);
        } else if (cmd.equals("deadline")) {
            String errorMessage = "☹ Incorrect format. Eg. deadline Do Project /by 29/8/2019 1800";
            String desc = getDesc(inputArr[1], " /by ", errorMessage);
            String dateTime = getDateTime(inputArr[1], " /by ", errorMessage);
            nextCommand = new AddCommand(cmd, desc, dateTime);
        } else if (cmd.equals("event")) {
            String errorMessage = "☹ Incorrect format. Eg. event Talk Show /at 29/8/2019 1800";
            String desc = getDesc(inputArr[1], " /at ", errorMessage);
            String dateTime = getDateTime(inputArr[1], " /at ", errorMessage);
            nextCommand = new AddCommand(cmd, desc, dateTime);
        } else if (cmd.equals("done")) {
            int index = getIndex(inputArr[1]);
            nextCommand = new DoneCommand(cmd, index);
        } else if (cmd.equals("delete")) {
            int index = getIndex(inputArr[1]);
            nextCommand = new DeleteCommand(cmd, index);
        } else if (cmd.equals("bye")) {
            //go with default command
        } else {
            //if command can't be recognised
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        return nextCommand;
    }

    private static String getDesc(String details, String delimiter, String errorMessage) throws DukeException {
        if (details.contains(delimiter)) {
            int index = details.indexOf(delimiter);
            String desc = details.substring(0, index).trim();
            return desc;
        } else {
            throw new DukeException(errorMessage);
        }
    }

    private static String getDateTime(String details, String delimiter, String errorMessage) throws DukeException {
        int index = details.indexOf(delimiter);
        String dateTime = details.substring(index + delimiter.length()).trim();
        if (isDateValid(dateTime)) {
            return dateTime;
        } else {
            throw new DukeException(errorMessage);
        }
    }

    private static boolean isDateValid(String dateTime) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime tempDateTime = LocalDateTime.parse(dateTime, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private static int getIndex(String indexStr) throws DukeException {
        try {
            return Integer.parseInt(indexStr);
        } catch (NumberFormatException e) {
            throw new DukeException("☹ Please enter a valid number.");
        }
    }

}
