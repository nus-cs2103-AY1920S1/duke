package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.exception.DukeException;

public class ExitCommand extends Command {
    public ExitCommand(String[] description) {
        super(description);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        isExit = true;
        return ui.showExit();
    }
}
