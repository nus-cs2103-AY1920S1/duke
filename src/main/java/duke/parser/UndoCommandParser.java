package duke.parser;

import duke.command.Command;
import duke.command.CommandHistoryStack;
import duke.command.IncompleteCommandException;
import duke.command.UnknownCommandException;

public class UndoCommandParser {
    public static Command parse(String command, CommandHistoryStack commandHistory) throws UnknownCommandException {
        if (!command.toLowerCase().equals("undo")) {
            throw new UnknownCommandException(command);
        }
        return commandHistory.pop();
    }
}
