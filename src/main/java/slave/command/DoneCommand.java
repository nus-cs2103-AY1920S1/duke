package slave.command;

import slave.elements.TaskList;
import slave.elements.Ui;

import slave.exception.DukeException;
import slave.exception.TaskAlreadyDoneException;
import slave.exception.TaskNotFoundException;

import slave.task.Task;

/**
 * Command which represents a done action on a particular task.
 */
public class DoneCommand extends Command {

    private int index;

    /**
     * Constructor for done command.
     *
     * @param index The index of the task that is going to be marked done.
     */
    public DoneCommand(int index) {
        this.commandType = CommandType.DONE;
        this.index = index;
    }

    /**
     * Executes by marking a particular task as done and prints to the user.
     *
     * @param tasks List containing current tasks.
     * @param ui User interface.
     * @throws DukeException For tasks that cannot be found or have already been done.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        try {
            tasks.getTaskByIndex(this.index);
        } catch (IndexOutOfBoundsException error) {
            throw new TaskNotFoundException("Task " + this.index);
        }
        if (tasks.getTaskByIndex(this.index).getIsDone()) {
            throw new TaskAlreadyDoneException("Task " + this.index);
        }
        tasks.setDoneInList(this.index);
        Task task = tasks.getTaskByIndex(this.index);
        ui.printDoneCommand(task);
    }
}
