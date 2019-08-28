package duke.command;

import duke.command.Command;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class DoneCommand extends Command {

    int index;

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
        index = Integer.parseInt(ui.getRemainingWords().trim());
        tasks.getTaskArrayList().get(index - 1).markAsDone();
        storage.writeData();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.getTaskArrayList().get(index - 1));
        ui.showLine();
    }

    public boolean isExit() {
        return false;
    }
}
