package duke.module;

import duke.command.Command;
import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;

import duke.date.DukeDate;

import duke.exception.DukeDateFormatException;
import duke.exception.DukeIOException;

import duke.exception.DukeIllegalCommandException;
import duke.task.Task;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.TodoTask;

/**
 * Parses various Strings into meaningful information.
 */
public class Parser {

    /** {@value ERROR_DATE_FORMAT} */
    private static final String ERROR_DATE_FORMAT = "☹ OOPS!!! Date must be in MM/DD/YYYY HHMM format.";
    /** {@value ERROR_SAVE_FILE_FORMAT} */
    private static final String ERROR_SAVE_FILE_FORMAT = "☹ OOPS!!! Saved file contains illegal formatting.";
    /** {@value ERROR_ILLEGAL_COMMAND} */
    private static final String ERROR_ILLEGAL_COMMAND = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";

    /**
     * This stores all the commands that Duke can understand and carry out.
     */
    private enum CommandType {
        LIST,
        DONE,
        FIND,
        TODO,
        EVENT,
        DEADLINE,
        DELETE,
        BYE;
    }

    /**
     * Parses the given date in String to a {@link DukeDate} object.
     * <p>
     * <b>Prerequisite: </b>String must be in MM/DD/YYYY HHMM form in 24-hour format.
     *
     * @param date String to be parsed.
     * @return {@link DukeDate} with information formatted in the date String.
     * @throws DukeDateFormatException When the date and time given is illegal or in the wrong format.
     */
    public static DukeDate parseToDate(String date) throws DukeDateFormatException {
        // Date format : MM/DD/YYYY HHMM
        String[] dateAndTime = date.split(" ");
        String[] dateFormat = dateAndTime[0].split("/");
        try {
            return new DukeDate(Integer.parseInt(dateFormat[2]),
                                Integer.parseInt(dateFormat[0]),
                                Integer.parseInt(dateFormat[1]),
                                Integer.parseInt(dateAndTime[1].substring(0, 2)),
                                Integer.parseInt(dateAndTime[1].substring(2)));
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DukeDateFormatException(ERROR_DATE_FORMAT);
        }
    }

    /**
     * Parses each line in the save file into the corresponding {@link Task} object.
     *
     * @param line A line in the save file.
     * @return {@link Task} corresponding to the line.
     * @throws DukeIOException When the lines in the save file is formatted incorrectly.
     */
    static Task parseToTask(String line) throws DukeIOException {
        try {
            String[] taskComponents = line.split("-");
            switch (taskComponents[0]) {
            case "T":
                return new TodoTask(taskComponents[2],
                        taskComponents[1].equals("1"));
            case "D":
                return new DeadlineTask(taskComponents[2],
                        taskComponents[1].equals("1"),
                        parseToDukeDate(taskComponents[3]));
            case "E":
                return new EventTask(taskComponents[2],
                        taskComponents[1].equals("1"),
                        parseToDukeDate(taskComponents[3]));
            default:
                throw new DukeIOException(ERROR_SAVE_FILE_FORMAT);
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeIOException(ERROR_SAVE_FILE_FORMAT);
        }
    }

    /**
     * Parses the date into a {@link DukeDate}.
     *
     * @param date Result of {@link DeadlineTask#getDateAsString()} or {@link EventTask#getDateAsString()}.
     * @return {@link DukeDate} corresponding to the date String.
     * @throws DukeIOException When the date in the save file is formatted incorrectly.
     */
    private static DukeDate parseToDukeDate(String date) throws DukeIOException {
        // dd MM, yyyy, hh:mm a
        try {
            String[] parsed = date.split(" |, |:");
            int hour = parsed[5].equals("AM")
                    ? Integer.parseInt(parsed[3])
                    : Integer.parseInt(parsed[3]) + 12;
            return new DukeDate(Integer.parseInt(parsed[2]),
                    DukeDate.Month.valueOf(parsed[1].toUpperCase()),
                    Integer.parseInt(parsed[0]),
                    hour,
                    Integer.parseInt(parsed[4]));
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | DukeDateFormatException e) {
            throw new DukeIOException(ERROR_SAVE_FILE_FORMAT);
        }
    }

    /**
     * Parses the inputted command into a {@link Command} object.
     *
     * @param command User inputted string to be parsed into a {@link Command} object.
     * @param description The rest of the user input;
     * @return The {@link Command} object corresponding to the user input.
     * @throws DukeIllegalCommandException When the string inputted is not a valid {@link CommandType}.
     */
    public static Command parseToCommand(String command, String description) throws DukeIllegalCommandException {
        try {
            switch (CommandType.valueOf(command.toUpperCase())) {
            case LIST:
                return new ListCommand();
            case DONE:
                return new DoneCommand(description);
            case FIND:
                return new FindCommand(description);
            case TODO:
                return new AddTodoCommand(description);
            case EVENT:
                return new AddEventCommand(description);
            case DEADLINE:
                return new AddDeadlineCommand(description);
            case DELETE:
                return new DeleteCommand(description);
            case BYE:
                return new ExitCommand();
            default:
                throw new DukeIllegalCommandException(ERROR_ILLEGAL_COMMAND);
            }
        } catch (IllegalArgumentException e) {
            throw new DukeIllegalCommandException(ERROR_ILLEGAL_COMMAND);
        }
    }

}
