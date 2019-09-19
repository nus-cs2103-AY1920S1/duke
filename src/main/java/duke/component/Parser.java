package duke.component;

import duke.command.Command;
import duke.command.CommandGenerator;
import duke.exception.DukeException;

/**
 * Encapsulates a parser which parses the input from the console.
 */
public class Parser {
    private CommandGenerator commandGenerator = new CommandGenerator();

    /**
     * Analyses the input from the console and returns the appropriate action to it.
     *
     * @param input the input from the console.
     * @return the appropriate action to the input, encapsulated in a Command object.
     * @throws DukeException if the parser cannot understand the user's input, i.e. the user's input or command
     *     is not supported by duke.Duke bot.
     */
    public Command parse(String input) throws DukeException {
        assert input != null : "Input cannot be null!";

        if (input.startsWith("todo")) {
            return commandGenerator.getAddCommandForToDo(input);
        } else if (input.startsWith("deadline")) {
            return commandGenerator.getAddCommandForDeadline(input);
        } else if (input.startsWith("event setDate")) {
            return commandGenerator.getConfirmEventDateCommand(input);
        } else if (input.startsWith("event")) {
            return commandGenerator.getAddCommandForEvent(input);
        } else if ("list".equals(input)) {
            return commandGenerator.getListCommand();
        } else if (input.startsWith("find")) {
            return commandGenerator.getFindCommand(input);
        } else if (input.startsWith("done")) {
            return commandGenerator.getDoneCommand(input);
        } else if (input.startsWith("delete")) {
            return commandGenerator.getDeleteCommand(input);
        } else if ("bye".equals(input)) {
            return commandGenerator.getExitCommand();
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
