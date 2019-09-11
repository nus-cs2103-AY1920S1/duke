package duke.command;

import duke.data.Storage;
import duke.object.Task;
import duke.object.TaskList;
import duke.user.Ui;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class SetDoneCommand extends Command {
    private int idx;

    public SetDoneCommand(int idx) {
        this.idx = idx;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task = tasks.getTask(this.idx).setDone();
        int days = task.getRecur();
        tasks.addTask(new Task(task.getType(), task.getDescription(), task.getDateTime().plusDays(days).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))));
        ui.confirmDone(tasks.getTask(this.idx).setDone());
        storage.saveTasks(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
