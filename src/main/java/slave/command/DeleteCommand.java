package slave.command;

import slave.elements.TaskList;
import slave.elements.Ui;

import slave.exception.DukeException;
import slave.exception.TaskNotFoundException;

import slave.task.Task;

/**
 * Represents a command to delete a particular task from storage or list.
 */
public class DeleteCommand extends Command {

    private int index;

    /**
     * Constructor for creating a delete command.
     *
     * @param index Index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.commandType = CommandType.DELETE;
        this.index = index;
    }

    /**
     * Executes by deleting a task from storage and taskList.
     *
     * @param tasks List containing current tasks.
     * @param ui User interface.
     * @throws DukeException For error in retrieving task from list due to invalid index.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        try {
            tasks.getTaskByIndex(this.index);
        } catch (IndexOutOfBoundsException error) {
            throw new TaskNotFoundException("Task " + this.index);
        }
        Task toRemove = tasks.getTaskByIndex(this.index);
        tasks.removeFromList(this.index);
        ui.printDeleteCommand(toRemove, tasks);
    }
}