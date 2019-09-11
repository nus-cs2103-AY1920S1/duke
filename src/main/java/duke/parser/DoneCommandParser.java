package duke.parser;

import duke.command.Command;
import duke.command.DoneCommand;
import duke.command.IncompleteCommandException;

public class DoneCommandParser {
    /**
     * Marks specified command based on idx of command and required format.
     * @param commandDescription - array of strings containing command description
     * @param size - size of current tasklist
     * @return Optional containing either valid command or null (when exception thrown)
     * @throws RuntimeException - contains both NumberFormatException and IndexOutOfBoundsException
     * @throws IncompleteCommandException - throws error if the command is not in complete format
     */
    public static Command parse(String[] commandDescription, int size)
            throws RuntimeException, IncompleteCommandException {
        checkCommandEmpty(commandDescription);
        int idx = Integer.parseInt(commandDescription[1]) - 1;
        if ((idx < 0) || (idx >= size)) {
            throw new IndexOutOfBoundsException();
        }
        return new DoneCommand(idx);
    }

    /**
     * Throws error if the given command is empty.
     * @param commandDescription - array of strings containing command description
     * @throws IncompleteCommandException - throws error if the command is not in complete format
     */
    private static void checkCommandEmpty(String[] commandDescription)
            throws IncompleteCommandException {
        if (commandDescription.length == 1) {
            throw new IncompleteCommandException("empty", commandDescription[0]);
        }
    }
}
