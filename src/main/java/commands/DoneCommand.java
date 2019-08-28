package commands;

import exceptions.DukeException;
import tasks.Task;
import utils.Storage;
import utils.TaskList;
import utils.Ui;

public class DoneCommand extends Command {
    private int taskId;

    public DoneCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String str;
        if (taskId > tasks.getSize()) {
            throw new DukeException("Please choose a task within the list");
        } else {
            Task doneTask = tasks.getTask(taskId - 1);
            doneTask.markAsDone();
            str = "Nice! I've marked this task as done:\n" + " " + doneTask.toString();
            ui.addBorder(str);
        }
        storage.writeToFile();
    }
}
