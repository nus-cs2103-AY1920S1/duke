package duke.command;

import duke.task.Task;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.printAddTask(task, tasks.size());
    }
}
