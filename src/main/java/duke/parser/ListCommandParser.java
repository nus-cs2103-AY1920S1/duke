package duke.parser;

import duke.command.Command;
import duke.command.ListCommand;
import duke.exception.InvalidCommandError;

import java.util.Optional;

public class ListCommandParser {

    /**
     * Parse list command based on required format
     * @return Optional containing either valid command or null (when exception thrown)
     * @throws InvalidCommandError - throws error if the command is in wrong format
     */
    public static Optional<Command> parse(String command) throws InvalidCommandError  {
        if (!command.toLowerCase().equals("list")) { throw new InvalidCommandError(command); }
        return Optional.of(new ListCommand());
    }
}
