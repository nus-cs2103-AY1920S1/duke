package duck.command;

import duck.util.Storage;
import duck.util.TaskList;
import duck.util.Ui;

import java.io.IOException;

public class ExitCommand extends Command {

    public ExitCommand() {
        isExit = true;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            storage.recordTasks(taskList);
        } catch (IOException e) {
            ui.showSavingError();
        }

    }

}
