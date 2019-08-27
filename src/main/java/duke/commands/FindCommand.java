package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UI;

public class FindCommand extends Command {
    String kw;

    public FindCommand(String kw) {
        this.kw = kw;
    }

    public void execute(TaskList tasks, UI ui, Storage storage) {
        String taskMessage = tasks.find(kw);
        ui.showFoundMessage(taskMessage);
    }

    public boolean isExit() {
        return false;
    }

}