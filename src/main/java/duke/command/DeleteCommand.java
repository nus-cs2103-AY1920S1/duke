package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.io.IOException;

/**
 * Handles command that requires deleting a task from the list of tasks.
 */
public class DeleteCommand extends Command {

    private int taskNum;

    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task taskChanged = tasks.deleteTask(taskNum);
        ui.showDeletedTask(taskChanged);
        ui.showNumTasks(tasks);
        try {
            storage.writeToFile(tasks);
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}