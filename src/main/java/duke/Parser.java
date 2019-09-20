package duke;

import duke.command.Command;
import duke.command.ByeCommand;
import duke.command.ListCommand;
import duke.command.DoneCommand;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.ChangeSaveFileCommand;
import duke.command.TodoCommand;
import duke.command.DeadlineCommand;
import duke.command.EventCommand;
import duke.exception.DukeException;
import duke.exception.InvalidCommandException;

class Parser {
    static Command parse(String input) throws DukeException {
        try {
            String[] tokens = input.split(" ", 2);
            String command = tokens[0].trim();

            switch (command) {
            case "bye":
                return new ByeCommand();
            case "list":
                return new ListCommand();
            case "done":
                return new DoneCommand(tokens[1]);
            case "delete":
                return new DeleteCommand(tokens[1]);
            case "find":
                return new FindCommand(tokens[1]);
            case "savefile":
                return new ChangeSaveFileCommand(tokens[1]);
            case "todo":
                return new TodoCommand(tokens[1]);
            case "deadline":
                return new DeadlineCommand(tokens[1]);
            case "event":
                return new EventCommand(tokens[1]);
            default:
                throw new InvalidCommandException(Ui.showInvalidCommand());
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandException(Ui.showInvalidCommand());
        }
    }
}
