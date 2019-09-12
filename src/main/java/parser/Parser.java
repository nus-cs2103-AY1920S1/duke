package parser;

import command.Command;
import command.DoneCommand;
import command.ListCommand;
import command.ToDoCommand;
import command.DeadlineCommand;
import command.EventCommand;
import command.DeleteCommand;
import command.ByeCommand;
import command.InvalidCommand;
import command.FindCommand;

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
        default:
            return new InvalidCommand();
        }
    }
}
