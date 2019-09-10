package duke.command;

import duke.task.Task;

import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a "todo", "event", or "deadline" command.
 */
public class AddCommand extends Command {
    /**
     * Constructor for task adding command.
     *
     * @param task Task to be added to the TaskList.
     */
    public AddCommand(Task task) {
        super(task);
    }

    /**
     * Adds new task from input to current list of Task.
     * Ui then prints the Task that was added to the TaskList and the number of tasks in the TaskList.
     *
     * @param tasks List of Tasks Duke is currently tracking.
     * @param ui User Interface of Duke that handles input and output to and from the command line.
     * @param storage Storage where the Tasks are retrieved from and stored to.
     */
    @Override
    public void executeCli(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(task);
        ui.printAddTaskMsg(task, tasks.size());
    }

    @Override
    public String executeGui(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(task);
        return ui.getAddTaskMsg(task, tasks.size());
    }
}
