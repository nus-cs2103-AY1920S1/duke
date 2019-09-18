package duke.utilities;

import duke.command.AddTagCommand;
import duke.command.Command;
import duke.command.CreateDeadlineCommand;
import duke.command.CreateEventCommand;
import duke.command.CreateTodoCommand;
import duke.command.DeleteTaskCommand;
import duke.command.ExitCommand;
import duke.command.FindTaskCommand;
import duke.command.ListCommand;
import duke.command.MarkAsDoneCommand;
import duke.exception.InvalidCommandException;


/**
 * Helper class for parsing Duke commands.
 */
public class Parser {

    /**
     * Parses and executes Duke commands.
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
        case "tag":
            return new AddTagCommand(commandInformation);
        default:
            throw new InvalidCommandException("I'm sorry, but I don't know what that means");
        }
    }
}
