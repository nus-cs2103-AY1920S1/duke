package duke.command;

import duke.task.*;
import duke.ui.*;
import duke.storage.*;

public class SendTasksCommand extends Command {

    public boolean isTerminated() {
        return false;
    }

    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        ui.sendMessage("Here are the tasks in your list: ");
        for (int i = 0; i < tasklist.size(); i ++) {
            Task item = tasklist.get(i);
            ui.sendMessage((i + 1) + "." + item.toString());
        }
    }

}
