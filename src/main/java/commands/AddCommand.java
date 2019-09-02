package commands;

import tasks.Task;
import storage.Storage;
import util.TaskList;
import ui.Ui;

import java.io.IOException;

/**
 * Encapsulates command for adding todo, deadline, and event tasks to list.
 *
 * @author atharvjoshi
 * @version CS2103 AY19/20 Sem 1 iP Week 4
 */
public class AddCommand extends Command {

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
     * @param tasks the task list the task is to be added to
     * @param ui the user interface associated with this run of Duke
     * @param storage the storage handler associated with this run of Duke
     * @throws IOException when file the list is to be written to is not found
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws IOException {
        if (this.task != null) {
            // add task to list
            tasks.add(this.task);

            // call UI to send message informing user that task has been added
            ui.showAddTaskMessage(this.task, tasks.size());

            // call storage to update the saved file of tasks
            storage.update(tasks);
        }
    }
}
