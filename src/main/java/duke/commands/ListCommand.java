package duke.commands;

import duke.Duke;
import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.tasks.TaskList;

public class ListCommand extends Command {

    private static final String TOO_MANY_ARGUMENTS = "☹ OOPS!!! The list command takes no arguments.";

    public static ListCommand create(String input, String[] args) throws DukeException {
        int numArgs = args.length;
        if (numArgs > 1) {
            throw new DukeException(TOO_MANY_ARGUMENTS);
        }
        return new ListCommand();
    }

    private ListCommand() {
        super();
    }

    public void execute(Storage storage, Ui ui, TaskList tasklist) {
        ui.say(tasklist.toString());
    }

}
