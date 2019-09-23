package duke.util;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.enums.CommandEnum;
import duke.exception.DukeException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Parser that converts strings to objects and vice-versa in the Duke Program.
 */
public class Parser {
    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    /**
     * Parses a String command and returns a Command object.
     *
     * @param line The line containing the String command.
     * @return The Command object corresponding to the input string.
     * @throws DukeException If the command is invalid.
     */
    public static Command parseCommand(String line) throws DukeException {
        // Get all inputs by splitting on a whitespaces delimiter
        String[] inputs = line.split("\\s+");

        // If there are no inputs, skip the current loop iteration and wait for another input
        if (inputs.length == 0) {
            return null;
        }

        // The first value in the inputs array is the command.
        // Retrieve the command and get its enum value.
        // Throw a DukeException if there is no corresponding enum value.
        CommandEnum commandEnum = null;
        try {
            commandEnum = CommandEnum.valueOf(inputs[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        switch (commandEnum) {
        case BYE:
            return new ByeCommand(line);
        case LIST:
            return new ListCommand(line);
        case FIND:
            return new FindCommand(line);
        case DONE:
            return new DoneCommand(line);
        case DELETE:
            return new DeleteCommand(line);
        case TODO:
            return new TodoCommand(line);
        case DEADLINE:
            return new DeadlineCommand(line);
        case EVENT:
            return new EventCommand(line);
        default:
            throw new DukeException("Unrecognized command");
        }
    }

    /**
     * Converts a string of format "dd/MM/yyyy HH:mm" into a java Date object.
     *
     * @param dateTimeString An input string in the format dd/MM/yyyy HH:mm
     * @return A datetime corresponding to the input string
     * @throws DukeException if the input dateTimeString is invalid
     */
    public static Date parseDateTime(String dateTimeString) throws DukeException {
        try {
            return DATE_FORMATTER.parse(dateTimeString);
        } catch (ParseException e) {
            throw new DukeException("Invalid Date Time format! Format should be: dd/MM/yyyy HH:mm");
        }
    }

    /**
     * Converts a Date object into a string in the format specified in DATE_FORMATTER.
     *
     * @param date The date to be converted to a string.
     * @return A string containing a formatted date.
     */
    public static String convertDateTimeToString(Date date) {
        return DATE_FORMATTER.format(date);
    }
}