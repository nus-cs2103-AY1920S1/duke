package duke.command;

import duke.model.Task;
import duke.storage.Storage;
import duke.ui.Ui;

import java.util.List;

public class DeleteCommand implements Command {
    private final int taskIndex;

    public DeleteCommand(String command) {
        taskIndex = Integer.parseInt(command);
    }

    @Override
    public void execute(List<Task> tasks, Ui ui, Storage storage) {
        Task t = tasks.get(taskIndex - 1);
        tasks.remove(taskIndex - 1);

        storage.save(tasks);

        ui.displaySuccessfullyRemovedTask("Noted. I've removed this task:", t, tasks.size());
    }
}
