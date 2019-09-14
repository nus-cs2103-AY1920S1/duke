package duke;

import duke.command.*;

public class CommandGenerator {

    public CommandGenerator() {
    }

    Command generateCommand(String fullCommand) throws DukeException {
        Parser parser = new Parser(fullCommand);
        if (parser.checkValidity()) {
            switch (parser.getCommandType()) {
            case "list":
                return new ListCommand(fullCommand);
            case "done":
                return new DoneCommand(fullCommand);
            case "delete":
                return new DeleteCommand(fullCommand);
            case "find":
                return new FindCommand(fullCommand);
            case "todo":
                return new TodoCommand(fullCommand);
            case "deadline":
                return new DeadlineCommand(fullCommand);
            case "event":
                return new EventCommand(fullCommand);
            default:
                assert false : parser.getCommandType();
            }
        }
        return new Command(fullCommand);
    }
}
