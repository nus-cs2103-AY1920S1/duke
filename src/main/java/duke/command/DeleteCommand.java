package duke.command;

import duke.exception.DukeException;
import duke.exception.EmptyTaskListException;
import duke.exception.InvalidIntegerTaskListException;
import duke.exception.NotAnIntegerTaskListException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.task.Task;

import java.io.IOException;

public class DeleteCommand extends Command {
    private int index;
    
    public DeleteCommand(int index) {
        this.index = index;
    }
    
    /**
     * Deletes a Task from the input TaskList.
     *
     * @param taskList The TaskList object passed from Duke.
     * @param ui The Ui object passed from Duke.
     * @param storage The Storage object passed from Duke.
     * @return The response String.
     * @throws DukeException A DukeException custom exception.
     * @throws IOException An IOException.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        if (taskList.getSize() == 0) {
            throw new EmptyTaskListException("OOPS!!! You have no tasks currently stored in your list!");
        }
        try {
            Task currentTask = taskList.deleteTask(index - 1);
            storage.writeSavedList(taskList.getList());
            return ui.showAfterDeletingTask(currentTask, taskList.getSize());
        } catch (NumberFormatException e) {
            throw new NotAnIntegerTaskListException("OOPS!!! Please enter an integer after 'delete'!");
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIntegerTaskListException("OOPS!!! Please enter a valid task number!");
        }
    }
}
