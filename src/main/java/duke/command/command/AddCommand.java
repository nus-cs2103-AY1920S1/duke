package duke.command.command;

import duke.command.Command;
import duke.task.Task;
import duke.task.TasksController;
import error.ui.UiException;

/***
 * <p>
 * Command to add tasks.
 * </p>
 */
public class AddCommand implements Command {
    private Task task;
    private TasksController tasksController;

    /***
     * <p>
     * AddCommand constructor.
     * </p>
     * @param tasksController controller for duke.task list to be added to.
     */
    public AddCommand(Task task, TasksController tasksController) {
        this.task = task;
        this.tasksController = tasksController;
    }

    /***
     * <p>
     * Adds duke.task to memory.
     * </p>
     * @return listen duke.command.
     */
    @Override
    public void execute() throws UiException {
        tasksController.addTask(task);
    }
}
