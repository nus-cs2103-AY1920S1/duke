package duke.parser;

import duke.command.Command;
import duke.command.ListCommand;
import duke.command.UnknownCommandException;

public class ListCommandParser {

    /**
     * Parse list command based on required format.
     * @return Optional containing either valid command or null (when exception thrown)
     */
    public static Command parse(String command) throws UnknownCommandException {
        if (!command.toLowerCase().equals("list")) {
            throw new UnknownCommandException(command);
        }
        return new ListCommand();
    }
}
