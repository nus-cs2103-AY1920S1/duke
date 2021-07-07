package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.task.TaskList;

public class ExitCommand extends Command {
    public ExitCommand() {
        super();
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return "Exiting duke. Your tasks will be saved.";
    }

    // @Override
    // public boolean isExit() {
    // return true;
    // }
}
