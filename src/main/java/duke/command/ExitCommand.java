package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {
    public void execute(Ui ui, Storage storage, TaskList taskList) {}

    @Override
    public boolean shouldContinue(){
        return false;
    }
}
