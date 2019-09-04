package duke.command;

import duke.error.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Storage;
import duke.Ui;

public class ExitCommand extends Command {
    protected String command;

    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return ui.showBye();
    }

    public boolean isExit() {
        return true;
    }
}