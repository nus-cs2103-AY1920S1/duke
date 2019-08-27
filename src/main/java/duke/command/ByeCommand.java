package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ByeCommand extends Command {
    public ByeCommand() {
        super();
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {

    }

    @Override
    public boolean isBye() {
        return true;
    }
}
