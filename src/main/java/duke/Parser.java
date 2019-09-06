package duke;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ListCommand;
import duke.command.FindCommand;

/**
 * Duke.Parser class deals with making sense of the user Duke.command.
 */
public class Parser {
    
    /**
     * A static method that parse the input Duke.command given by the user and
     * deals with the Duke.command respectively.
     *
     * @param fullCommand makes sense of the input given by the user.
     * @return a Command which will then be executed.
     */
    public static Command parse(String fullCommand) {
        if (fullCommand.equals("list")) {
            return new ListCommand(fullCommand);
        } else if (fullCommand.startsWith("done")) {
            return new DoneCommand(fullCommand);
        } else if (fullCommand.startsWith("delete")) {
            return new DeleteCommand(fullCommand);
        } else if (fullCommand.startsWith("find")) {
            return new FindCommand(fullCommand);
        } else if (fullCommand.equals("bye")) {
            return new ByeCommand(fullCommand);
        } else {
            return new AddCommand(fullCommand);
        }
    }
}
