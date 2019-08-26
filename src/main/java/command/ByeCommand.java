package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class ByeCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("     Bye. Hope to see you again soon!");
        storage.save(tasks);
    }

    public boolean isExit() {
        return true;
    }
}
