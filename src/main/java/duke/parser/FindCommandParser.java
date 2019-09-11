package duke.parser;

import duke.command.Command;
import duke.command.FindCommand;
import duke.command.IncompleteCommandException;

public class FindCommandParser {

    /**
     * Parse find command.
     * @param commandDescription - array of strings containing command description
     * @return Optional containing either valid command
     * @throws IncompleteCommandException - throws error if the command is not in complete format
     */
    public static Command parse(String[] commandDescription) throws IncompleteCommandException {
        checkCommandEmpty(commandDescription);
        return new FindCommand(commandDescription[1]);
    }

    /**
     * Throws error if the given command is empty.
     * @param commandDescription - array of strings containing command description
     * @throws IncompleteCommandException - throws error if the command is not in complete format
     */
    private static void checkCommandEmpty(String[] commandDescription) throws IncompleteCommandException {
        if (commandDescription.length == 1) {
            throw new IncompleteCommandException("empty", commandDescription[0]);
        }
    }
}
