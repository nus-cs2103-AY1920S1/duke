package kappa.command;

import kappa.elements.TaskList;
import kappa.elements.Ui;

import kappa.exception.KappaException;
import kappa.exception.TaskAlreadyDoneException;
import kappa.exception.TaskNotFoundException;

import kappa.task.Task;

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
     * @throws KappaException For tasks that cannot be found or have already been done.
     * @return String containing Kappa's formatted dialog.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) throws KappaException {
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
        return ui.printDoneCommand(task);
    }
}
