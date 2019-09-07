package duke.utilities;

import duke.command.*;
import duke.exception.InvalidCommandException;


/**
 * Helper class for parsing Duke commands
 */
public class Parser {

    /**
     * Parses and executes Duke commands
     *
     * @param fullCommand A <code>String</code> representing the full input command
     */
    public static Command parse(String fullCommand) throws InvalidCommandException {

        String[] parts = fullCommand.split(" ");
        String command = parts[0];
        String commandInformation = fullCommand.substring(command.length()).stripLeading();

        switch (command) {
        case "bye":
            return new ExitCommand(commandInformation);
        case "list":
            return new ListCommand(commandInformation);
        case "done":
            return new MarkAsDoneCommand(commandInformation);
        case "delete":
            return new DeleteTaskCommand(commandInformation);
        case "todo":
            return new CreateTodoCommand(commandInformation);
        case "deadline":
            return new CreateDeadlineCommand(commandInformation);
        case "event":
            return new CreateEventCommand(commandInformation);
        case "find":
            return new FindTaskCommand(commandInformation);
        default:
            throw new InvalidCommandException("I'm sorry, but I don't know what that means");
        }
    }
}
