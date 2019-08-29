import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.List;

public class Parser {
    //example input: 2/12/2019 1800
    public static LocalDateTime parseDateTime(String dateTimeStr) throws DukeException {
        try {
            String[] strSplit = dateTimeStr.split(" ");
            String[] date = strSplit[0].split("/");
            String time = strSplit[1];

            int year = Integer.parseInt(date[2]);
            int month = Integer.parseInt(date[1]);
            int day = Integer.parseInt(date[0]);
            int hours = Integer.parseInt(time.substring(0, 2));
            int mins = Integer.parseInt(time.substring(2));
            return LocalDateTime.of(year, month, day, hours, mins);
        } catch (ArrayIndexOutOfBoundsException | DateTimeException | NumberFormatException e) {
            throw new DukeException("Invalid Date-Time format.\n" +
                    "Please use DD/MM/YYYY HHMM E.g. [2/12/2019 1800]");
        }
    }

    //method to convert date object to string to append to text file
    public static String toFileDateTime(LocalDateTime dateObj) {
        return dateObj.getDayOfMonth() + "/" + dateObj.getMonthValue() + "/" + dateObj.getYear() + " "
                + String.format("%02d", dateObj.getHour())
                + String.format("%02d", dateObj.getMinute());
    }

    //method to print date in a nice string format
    public static String printDate(LocalDateTime dateObj) {
        return dateObj.getDayOfMonth() + " " + dateObj.getMonth() +
                " " + dateObj.getYear() + " " + String.format("%02d", dateObj.getHour())
                + String.format("%02d", dateObj.getMinute());
    }

    public static int parseTaskInt(String str, List<Task> taskList) throws DukeException {
        int taskInt;
        try {
            taskInt = Integer.parseInt(str);
            if (taskInt > taskList.size() || taskInt <= 0) {
                throw new DukeException("That task number does not exist, please try again");
            }
        } catch (NumberFormatException e) {
            throw new DukeException("Please state a valid task number");
        }

        return taskInt;
    }

    public static Command parseCommand(String inputString) throws DukeException { //receives scanned string immediately
        String[] strSplit = inputString.split(" ", 2); //get first word and remaining
        String command = strSplit[0];

        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new GetListCommand();
        case "done":
            try {
                return new DoneCommand(strSplit[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Please enter a Task Number");
            }
        case "delete":
            try {
                return new DeleteCommand(strSplit[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Please enter a Task Number");
            }

        case "todo":
            try {
                return new ToDoCommand(strSplit[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty");
            }
        case "deadline":
            try {
                return new DeadlineCommand(strSplit[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty");
            }
        case "event":
            try {
                return new EventCommand(strSplit[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty");
            }

        default:
            return new UnknownCommand();
        }

    }
}
