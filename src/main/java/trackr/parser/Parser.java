package trackr.parser;

import trackr.command.Command;
import trackr.command.CompleteCommand;
import trackr.command.DeadlineCommand;
import trackr.command.EventCommand;
import trackr.command.ExitCommand;
import trackr.command.FindCommand;
import trackr.command.HelpCommand;
import trackr.command.ListCommand;
import trackr.command.RemoveCommand;
import trackr.command.TodoCommand;
import trackr.command.UndoCommand;
import trackr.command.UpdateCommand;
import trackr.exception.TrackrException;

/**
 * The Parser class is used to decode user input. The parser class then retrieves the appropriate
 * command.
 */
public class Parser {

    /**
     * This method is used to retrieve the command from the user input.
     * @return Command Command object after processing user input
     */
    public static Command parse(String input) {
        String command = getCommand(input);
        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
        case "l":
            return new ListCommand(input);
        case "complete":
        case "c":
            return new CompleteCommand(input);
        case "todo":
        case "t":
            return new TodoCommand(input);
        case "deadline":
        case "d":
            return new DeadlineCommand(input);
        case "event":
        case "e":
            return new EventCommand(input);
        case "remove":
        case "r":
            return new RemoveCommand(input);
        case "find":
        case "f":
            return new FindCommand(input);
        case "help":
            return new HelpCommand();
        case "update":
        case "u":
            return new UpdateCommand(input);
        case "undo":
            return new UndoCommand();
        default:
            throw new TrackrException(":( OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static String getCommand(String input) {
        String[] inputStringArr = input.split(" ");
        return inputStringArr[0];
    }
}
