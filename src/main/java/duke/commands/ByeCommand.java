package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.tasks.TaskList;

public class ByeCommand extends Command{

    private static final String TOO_MANY_ARGUMENTS = "The bye command takes no arguments.";

    public static ByeCommand create(String input, String[] args) throws DukeException {
        int numArgs = args.length;
        if (numArgs > 1) {
            throw new DukeException(TOO_MANY_ARGUMENTS);
        }
        return new ByeCommand();
    }

    private ByeCommand() {
        super();
    }

    public void execute(Storage s, Ui ui, TaskList tasklist) {
        this.isExit = true;
    }
}
