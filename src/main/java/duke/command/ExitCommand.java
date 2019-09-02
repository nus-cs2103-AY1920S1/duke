package duke.command;

import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;
import duke.exception.DukeException;

public class ExitCommand extends Command {

    public ExitCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.showBye();
        taskList.writeTo(storage);
    }
}
