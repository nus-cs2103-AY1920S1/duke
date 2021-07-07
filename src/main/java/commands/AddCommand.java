package commands;

import tasks.Task;
import storage.Storage;
import util.TaskList;
import ui.Ui;

import java.io.IOException;

/**
 * Encapsulates command for adding to-do, deadline, and event tasks to list.
 *
 * @author atharvjoshi
 * @version CS2103 AY19/20 Sem 1 iP Week 4
 */
public class AddCommand extends UndoableCommand {

    /** The task created in response to this command. */
    private Task task;

    /**
     * Initialises an add command.
     * @param imperative the term used to identify command type
     * @param task the task to be added
     */
    public AddCommand(String imperative, Task task) {
        super(imperative);
        this.task = task;
    }

    /**
     * Executes the add command task by adding task to list, sending a message
     * to be printed on the user interface, and asking storage to update the
     * task list on the hard drive.
     * @param tasks the task list the task is to be added to.
     * @param ui the user interface associated with this run of Duke.
     * @param storage the storage handler associated with this run of Duke.
     * @throws IOException when file the list is to be written to is not found.
     * @return Duke's response to the user command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage)
            throws IOException {
        tasks.add(this.task);

        // update the saved file of tasks
        storage.update(tasks);

        // inform the user that task has been added
        return ui.showAddTaskMessage(this.task, tasks.size());
    }

    /**
     * Undos the command by deleting the task that was added.
     *
     * @param tasks the task list the task is to be added to.
     * @param ui the user interface associated with this run of Duke.
     * @param storage the storage handler associated with this run of Duke.
     * @return Duke's response to user command.
     * @throws IOException when file the list is to be written to is not found.
     */
    public String undo(TaskList tasks, Ui ui, Storage storage)
            throws IOException {
        int listSize = tasks.size();
        tasks.remove(listSize - 1);

        storage.update(tasks);

        return ui.showCommandUndoneMessage(tasks);
    }
}
