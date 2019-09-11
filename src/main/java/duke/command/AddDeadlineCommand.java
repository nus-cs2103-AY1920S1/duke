package duke.command;

import duke.data.Storage;
import duke.object.TaskList;
import duke.object.Task;
import duke.user.Ui;

import java.io.IOException;

public class AddDeadlineCommand extends Command {
    private String description;
    private String dateTime;

    public AddDeadlineCommand(String description, String dateTime) {
        this.description = description;
        this.dateTime = dateTime;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.confirmDeadlineAdded(tasks.addTask(new Task('D', description, dateTime)), tasks);
        storage.saveTasks(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
