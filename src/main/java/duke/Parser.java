package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import commands.*;

import exceptions.DukeException;

/**
 * Parser is a class that handles the parsing of user input.
 * It contains a method parse that instantiates and returns
 * the appropriate type of Command object associated with the user input.
 */
public class Parser {

    /**
     * Returns a Command object that is one of 6 types:
     * AddCommand, DeleteCommand, DoneCommand,
     * FindCommand, ExitCommand and ListCommand.
     * The type of Command object returned is based on the user input:
     * add [description] [datetime] (datetime only required for Event and Deadline Tasks),
     * delete [task number], done [task number], find [keywords],
     * exit, and list commands respectively.
     *
     * @param fullCommand the line of user input.
     * @return a Command object associated with user input.
     * @throws DukeException  If there is invalid input.
     */
    public static Command parse(String fullCommand, Duke duke) throws DukeException {
        String[] commandArr = fullCommand.split(" ", 2);
        switch (commandArr[0]) {
        case "help":
            return new HelpCommand(commandArr);
        case "bye":
            return new ExitCommand(commandArr, duke);
        case "list":
            return new ListCommand(commandArr);
        case "clear":
            return new ClearCommand(commandArr);
        case "undo":
            return new UndoCommand(commandArr);
        case "redo":
            return new RedoCommand(commandArr);
        case "delete":
            return new DeleteCommand(commandArr);
        case "done":
            return new DoneCommand(commandArr);
        case "find":
            return new FindCommand(commandArr);
        case "todo":
        case "deadline":
        case "event":
            return new AddCommand(commandArr);
        default:
            throw new DukeException("     \u2639 OOPS!!! I'm sorry, "
                    + "but I don't know what that means :-(");
        }
    }

    /**
     * Return a LocalDateTime object representing the time
     * an Event or Deadline takes place.
     *
     * @param dateTime String supplied by user representing date-time of a task.
     * @return LocalDateTime object representing the time an Event or Deadline takes place.
     * @throws DateTimeParseException invalid date-time format supplied by the user.
     */
    public static LocalDateTime parseDateTime(String dateTime) throws DateTimeParseException {
        LocalDateTime localDateTime;

        // Try parsing for different date time formats
        // to give the user greater freedom to type dateTimes in
        // a variety of formats

        // Parse for format of type: 07/10/1997 0839
        try {
            localDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"));
            return localDateTime;
        } catch (DateTimeParseException ignored) {
            // Ignore the exception because date time keyed in by user
            // could be considered valid by subsequent parses
        }

        // Parse for format of type: 07101997 0839
        try {
            localDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("ddMMyyyy HHmm"));
            return localDateTime;
        } catch (DateTimeParseException ignored) {
            // Ignore the exception because date time keyed in by user
            // could be considered valid by subsequent parses
        }

        // Parse for format of type: 07 10 1997 0839
        try {
            localDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("dd MM yyyy HHmm"));
            return localDateTime;
        } catch (DateTimeParseException ignored) {
            // Ignore the exception because date time keyed in by user
            // could be considered valid by subsequent parses
        }

        // Parse for format of type: 7 October 1997 0839
        try {
            localDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("d LLLL yyyy HHmm"));
            return localDateTime;
        } catch (DateTimeParseException ignored) {
            // Ignore the exception because date time keyed in by user
            // could be considered valid by subsequent parses
        }

        // Parse for format of type: 07/10/1997 8:39AM
        try {
            localDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("dd/MM/yyyy h:mma"));
            return localDateTime;
        } catch (DateTimeParseException ignored) {
            // Ignore the exception because date time keyed in by user
            // could be considered valid by subsequent parses
        }

        // Parse for format of type: 07101997 8:39AM
        try {
            localDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("ddMMyyyy h:mma"));
            return localDateTime;
        } catch (DateTimeParseException ignored) {
            // Ignore the exception because date time keyed in by user
            // could be considered valid by subsequent parses
        }

        // Parse for format of type: 07 10 1997 8:39PM
        try {
            localDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("dd MM yyyy h:mma"));
            return localDateTime;
        } catch (DateTimeParseException ignored) {
            // Ignore the exception because date time keyed in by user
            // could be considered valid by subsequent parses
        }

        // Parse for format of type: 7 October 1997 8:39PM
        localDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("d LLLL yyyy h:mma"));
        return localDateTime;
    }

}
