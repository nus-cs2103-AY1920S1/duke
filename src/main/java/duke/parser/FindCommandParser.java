package duke.parser;

import duke.command.Command;
import duke.command.FindCommand;

import java.util.Optional;

public class FindCommandParser {

    /**
     * Parse find command
     * @param commandDescription - array of strings containing command description
     * @return Optional containing either valid command
     */
    public static Optional<Command> parse(String[] commandDescription) {
        return Optional.of(new FindCommand(commandDescription[1]));
    }
}
