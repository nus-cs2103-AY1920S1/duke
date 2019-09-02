package duke.parser;

import duke.command.Command;
import duke.command.ListCommand;

import java.util.Optional;

public class ListCommandParser {

    /**
     * Parse list command based on required format
     * @return Optional containing either valid command or null (when exception thrown)
     */
    public static Optional<Command> parse(String command) {
        if (!command.toLowerCase().equals("list")) {
            System.out.println("☹ OOPS!!! That statement is invalid! Did you mean \"List\"?");
            return Optional.empty();
        }
        return Optional.of(new ListCommand());
    }
}
