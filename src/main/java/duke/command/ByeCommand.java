package duke.command;

import duke.DukeException;
import duke.TaskList;

public class ByeCommand extends Command {

    public static final String name = "bye";

    public ByeCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        System.exit(0);
        return "Bye~~";
    }
}
