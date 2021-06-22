package parser;

import command.ByeCommand;
import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.DoneCommand;
import command.EventCommand;
import command.FindCommand;
import command.InvalidCommand;
import command.ListCommand;
import command.ToDoCommand;
import command.UndoCommand;

/**
 * Parser to understand user inputs.
 */
public class Parser {

    /**
     * Parser is responsible for understanding the user's input. It will parse the
     * input and react with a suitable command.
     * @param userInput users's input as String
     * @return the respective Command.
     * @See Command
     */
    public static Command parse(String userInput) {
        String[] input = userInput.split(" ");
        switch (input[0]) {
        case "list":
            return new ListCommand();
        case "done":
            return new DoneCommand(input);
        case "todo":
            return new ToDoCommand(input);
        case "deadline":
            return new DeadlineCommand(input);
        case "event":
            return new EventCommand(input);
        case "delete":
            return new DeleteCommand(input);
        case "bye":
            return new ByeCommand();
        case "find":
            return new FindCommand(input);
        case "undo":
            return new UndoCommand();
        default:
            return new InvalidCommand();
        }
    }
}
