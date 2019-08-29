package duke;

import duke.command.*;
import duke.exception.DukeException;

public class Parser {

    public static Command parse(String fullCommand) throws DukeException {

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
                    return new InvalidCommand();
            }

    }

}
