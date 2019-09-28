package duke.parser;

import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.IncompleteCommandException;

public class DeleteCommandParser {

    /**
     * Parse delete command based on format.
     * @param commandDescription - array of strings containing command description
     * @param size - size of current tasklist
     * @return Optional containing either valid command or null (when exception thrown)
     * @throws RuntimeException - contains both NumberFormatException and IndexOutOfBoundsException
     */
    public static Command parse(String[] commandDescription, int size)
            throws RuntimeException, IncompleteCommandException {
        checkCommandEmpty(commandDescription);
        int idx = Integer.parseInt(commandDescription[1]) - 1;
        if ((idx < 0) || (idx >= size)) {
            throw new IndexOutOfBoundsException();
        }
        return new DeleteCommand(idx);
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
