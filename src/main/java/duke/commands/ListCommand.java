package duke.commands;

import duke.ui.UI;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import java.io.IOException;

public class ListCommand extends Command {

    public void execute(TaskList tasks, UI ui, Storage storage) {
        try {
            ui.showMessage(ui.padMessage(tasks.getTasks()));
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            ui.showMessage(e.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }

}