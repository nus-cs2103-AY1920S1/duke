package parser;

import commands.AddCommand;
import commands.CloneCommand;
import commands.DeleteCommand;
import commands.DoneCommand;
import commands.ExitCommand;
import commands.FindCommand;
import commands.ListCommand;
import commands.UpdateCommand;
import commands.Command;
import exception.DukeException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Class for the Parser which parsers commands.
 */
public class Parser {

    /**
     * Parses the command given by the user.
     *
     * @param input the string representation of the command input by the user
     * @return the command object given by the input
     * @throws DukeException thrown when command is invalid
     */
    public static Command parse(String input) throws DukeException {
        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.length() <= 3) {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        } else if (input.substring(0, 4).equals("find")) {
            return new FindCommand(input.substring(5));
        } else if (input.length() >= 6 && input.substring(0,4).equals("done")) {
            return new DoneCommand(Integer.parseInt(input.substring(5)));
        } else if (input.length() >= 7 && input.substring(0,5).equals("clone")) {
            return new CloneCommand(Integer.parseInt(input.substring(6)));
        } else if ((input.length() >= 8 && input.substring(0,6).equals("delete"))) {
            return new DeleteCommand(Integer.parseInt(input.substring(7)));
        } else if (input.length() >= 8 && input.substring(0,6).equals("update")) {
            return new UpdateCommand(Integer.parseInt(input.substring(7,8)), input.substring(8));
        } else if (input.equals("list")) {
            return new ListCommand();
        } else {
            return new AddCommand(input);
        }
    }

    /**
     * Parses the add command.
     *
     * @param input the string representation of the add command
     *              Format: type | description /(by||at) datetime
     * @return the type of task to be added
     * @throws DukeException thrown when input length is less than 4
     * @throws ParseException thrown when datetime is in incorrect format
     */
    public static Task parseAddCommand(String input) throws DukeException, ParseException {
        int idxOfSlash;
        if (isDeadline(input)) { // add Deadline
            idxOfSlash = input.indexOf("/by ");
            if (!isSlashPresent(idxOfSlash)) {
                throw new DukeException("The description of the deadline is incorrect.");
            }
            return new Deadline(input.substring(9, idxOfSlash - 1), parseDateTime(input, idxOfSlash));
        } else if (isEvent(input)) { // add Event
            idxOfSlash = input.indexOf("/at ");
            if (!isSlashPresent(idxOfSlash)) {
                throw new DukeException("The description of the event is incorrect.");
            }
            return new Event(input.substring(6, idxOfSlash - 1), parseDateTime(input, idxOfSlash));
        } else if (isToDo(input)) { // add ToDos
            try {
                return new ToDo(input.substring(5));
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static boolean isDeadline(String input) {
        return input.length() >= 8 && input.substring(0, 8).equals("deadline");
    }

    private static boolean isEvent(String input) {
        return input.length() >= 5 && input.substring(0, 5).equals("event");
    }

    private static boolean isToDo(String input) {
        return input.substring(0, 4).equals("todo");
    }

    private static boolean isSlashPresent(int idxOfSlash) {
        return idxOfSlash != -1;
    }

    private static String parseDateTime(String input, int idxOfSlash) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HHmm");
        return format.format(format.parse(input.substring(idxOfSlash + 4)));
    }

}
