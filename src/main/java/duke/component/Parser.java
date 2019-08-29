package duke.component;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.QueryCommand;
import duke.command.UpdateCommand;
import duke.exception.DukeException;

/**
 * Encapsulates a parser which parses the input from the console.
 */
public class Parser {

    /**
     * Analyses the input from the console and returns the appropriate action to it.
     *
     * @param input the input from the console.
     * @return the appropriate action to the input, encapsulated in a Command object.
     * @throws DukeException if the parser cannot understand the user's input, i.e. the user's input or command
     * is not supported by Duke bot.
     */
    public Command parse(String input) throws DukeException {
        if (input.startsWith("todo")) {
            return new AddCommand(AddCommand.AddType.TODO, input);
        } else if (input.startsWith("deadline")) {
            return new AddCommand(AddCommand.AddType.DEADLINE, input);
        } else if (input.startsWith("event")) {
            return new AddCommand(AddCommand.AddType.EVENT, input);
        } else if ("list".equals(input)) {
            return new QueryCommand(QueryCommand.QueryType.LIST_ALL, "");
        } else if (input.startsWith("find")) {
            return new QueryCommand(QueryCommand.QueryType.FIND_BY_KEYWORD, input);
        } else if (input.startsWith("done")) {
            return new UpdateCommand(UpdateCommand.UpdateType.DONE, input);
        } else if (input.startsWith("delete")) {
            return new DeleteCommand(DeleteCommand.DeleteType.INDEX, input);
        } else if ("bye".equals(input)) {
            return new ExitCommand();
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
