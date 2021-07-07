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
import duke.command.RedoCommand;
import duke.command.UndoCommand;

import duke.date.DukeDate;

import duke.exception.DukeDateFormatException;
import duke.exception.DukeIoException;
import duke.exception.DukeIllegalCommandException;

import duke.task.Task;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.TodoTask;

/**
 * Parses various Strings into meaningful information.
 */
public class Parser {

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
        UNDO,
        REDO,
        BYE;
    }

    /**
     * Parses the given date in String to a {@link DukeDate} object.
     *
     * <p><b>Prerequisite: </b>String must be in MM/DD/YYYY HHMM form in 24-hour format.
     *
     * @param date String to be parsed.
     * @return {@link DukeDate} with information formatted in the date String.
     * @throws DukeDateFormatException When the date and time given is illegal or in the wrong format.
     */
    public static DukeDate parseToDate(String date) throws DukeDateFormatException {
        // Date format : MM/DD/YYYY HHMM
        assert date != null : "Parser.java (line 54) : date should not be null";
        String[] dateAndTime = date.split(" ");
        String[] dateFormat = dateAndTime[0].split("/");
        try {
            int split = dateAndTime[1].length() / 2;
            return new DukeDate(Integer.parseInt(dateFormat[2]),
                                Integer.parseInt(dateFormat[0]),
                                Integer.parseInt(dateFormat[1]),
                                Integer.parseInt(dateAndTime[1].substring(0, split)),
                                Integer.parseInt(dateAndTime[1].substring(split)));
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DukeDateFormatException(AutoResponse.ERROR_DATE_FORMAT);
        }
    }

    /**
     * Parses each line in the save file into the corresponding {@link Task} object.
     *
     * @param line A line in the save file.
     * @return {@link Task} corresponding to the line.
     * @throws DukeIoException When the lines in the save file is formatted incorrectly.
     */
    static Task parseToTask(String line) throws DukeIoException {
        assert line != null : "Parser.java (line 76) : line should not be null";
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
                throw new DukeIoException(AutoResponse.ERROR_SAVE_FILE_FORMAT);
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeIoException(AutoResponse.ERROR_SAVE_FILE_FORMAT);
        }
    }

    /**
     * Parses the date into a {@link DukeDate}.
     *
     * @param date Result of {@link DeadlineTask#getDateAsString()} or {@link EventTask#getDateAsString()}.
     * @return {@link DukeDate} corresponding to the date String.
     * @throws DukeIoException When the date in the save file is formatted incorrectly.
     */
    private static DukeDate parseToDukeDate(String date) throws DukeIoException {
        // dd MM, yyyy, hh:mm a
        assert date != null : "Parser.java (line 108) : date should not be null";
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
            throw new DukeIoException(AutoResponse.ERROR_SAVE_FILE_FORMAT);
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
        assert command != null : "Parser.java (line 138) : command should not be null";
        assert description != null : "Parser.java (line 139) : description should not be null";
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
            case UNDO:
                return new UndoCommand();
            case REDO:
                return new RedoCommand();
            case BYE:
                return new ExitCommand();
            default:
                throw new DukeIllegalCommandException(AutoResponse.ERROR_ILLEGAL_COMMAND);
            }
        } catch (IllegalArgumentException e) {
            throw new DukeIllegalCommandException(AutoResponse.ERROR_ILLEGAL_COMMAND);
        }
    }

}
