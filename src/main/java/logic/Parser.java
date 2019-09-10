package logic;

import commands.*;
import task.Task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Deals with making sense of user command.
 */

public class Parser {
    /**
     * Receives string format of a dateTime and parses it to an object.
     *
     * @param dateTimeStr String of dateTime. E.g. 2/12/2019 1800
     * @return LocalDateTime variable that is stored in Deadline/Event Obj
     * @throws DukeException If given dateTimeStr is of an incorrect format
     */
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
            throw new DukeException(DukeStrings.INVALID_DATETIME_FORMAT);
        }
    }

    /**
     * Converts date object to string that is written on txt file.
     *
     * @param dateObj LocalDateTime Object
     * @return DateTime string E.g. 10/12/2019 0830
     */
    public static String toFileDateTime(LocalDateTime dateObj) {
        return dateObj.getDayOfMonth() + "/" + dateObj.getMonthValue()
                + "/" + dateObj.getYear() + " "
                + String.format("%02d", dateObj.getHour())
                + String.format("%02d", dateObj.getMinute());
    }

    /**
     * Prints date-time in a legible format on the CLI.
     *
     * @param dateObj LocalDateTime Object
     * @return Date String e.g. 10 DECEMBER 2019 0830
     */
    public static String printDate(LocalDateTime dateObj) {
        return dateObj.getDayOfMonth() + " " + dateObj.getMonth()
                + " " + dateObj.getYear() + " " + String.format("%02d", dateObj.getHour())
                + String.format("%02d", dateObj.getMinute());
    }

    /**
     * Attempts to convert input string into an int.
     *
     * @param str      String to be parsed
     * @param taskList List of Tasks
     * @return Int of Task in the TaskList
     * @throws DukeException If task number doesnt exist
     */
    public static int parseTaskInt(String str, List<Task> taskList) throws DukeException {
        int taskInt;

        try {
            taskInt = Integer.parseInt(str);
            if (taskInt > taskList.size() || taskInt <= 0) {
                throw new DukeException(DukeStrings.NONEXISTENT_TASK_NUMBER);
            }
        } catch (NumberFormatException e) {
            throw new DukeException(DukeStrings.INVALID_TASK_NUMBER);
        }

        return taskInt;
    }

    /**
     * Massive switch statement to read user inputs.
     *
     * @param inputString Input read from scanner
     * @return Command Object to be executed
     * @throws DukeException If input is invalid
     */
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
                throw new DukeException(DukeStrings.INVALID_TASK_NUMBER);
            }
        case "delete":
            try {
                return new DeleteCommand(strSplit[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException(DukeStrings.INVALID_TASK_NUMBER);
            }
        case "todo":
            try {
                return new ToDoCommand(strSplit[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException(DukeStrings.TODO_EMPTY);
            }
        case "deadline":
            try {
                return new DeadlineCommand(strSplit[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException(DukeStrings.DEADLINE_EMPTY);
            }
        case "event":
            try {
                return new EventCommand(strSplit[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException(DukeStrings.EVENT_EMPTY);
            }
        case "find":
            try {
                return new FindCommand(strSplit[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException(DukeStrings.FIND_EMPTY);
            }
        default:
            return new UnknownCommand();
        }

    }
}
