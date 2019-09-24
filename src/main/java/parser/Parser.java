package parser;



import command.Command;
import command.ByeCommand;
import command.ListCommand;
import command.DoneCommand;
import command.ToDoCommand;
import command.DeadlineCommand;
import command.EventCommand;
import command.DeleteCommand;
import command.FindCommand;
import command.RescheduleCommand;
import exception.DukeException;
import exception.InvalidInputException;

/**
 * Parser for reading user input and understand which command user is trying to use.
 */
public class Parser {

    /**
     * Reads a single line of user input and creates the relevant command.
     * @param rawString user input to create a command
     * @return command created if user input is successfully parsed
     * @throws DukeException if user enters an invalid input and suggests available commands instead
     */
    public static Command parse(String rawString) throws DukeException {
        if (rawString.equals("bye")) {
            return new ByeCommand();
        } else if (rawString.equals("list")) {
            return new ListCommand();
        } else if (rawString.startsWith("done ")) {
            return new DoneCommand(rawString);
        } else if (rawString.startsWith("todo")) {
            return new ToDoCommand(rawString);
        } else if (rawString.startsWith("deadline ")) {
            return new DeadlineCommand(rawString);
        } else if (rawString.startsWith("event ")) {
            return new EventCommand(rawString);
        } else if (rawString.startsWith("delete ")) {
            return new DeleteCommand(rawString);
        } else if (rawString.startsWith("find ")) {
            return new FindCommand(rawString);
        } else if (rawString.startsWith("reschedule ")) {
            return new RescheduleCommand(rawString);
        } else {
            throw new InvalidInputException("Invalid command! Try the commands: bye, list, done, todo, deadline, "
                    + "event, find, delete or reschedule and their respective formats!");
        }
    }
}