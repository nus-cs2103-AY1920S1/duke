package duke.commands;

import duke.ui.UI;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.tasks.Deadline;
import java.io.IOException;

public class DeadlineCommand extends Command {
    Deadline dl;

    public DeadlineCommand(Deadline dl) {
        this.dl = dl;
    }

    public void execute(TaskList tasks, UI ui, Storage storage) {
        try {
            String taskMessage = tasks.addDeadline(dl);
            ui.showAddedMessage(taskMessage, tasks.getTasksSize());
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            ui.showMessage(e.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }

}