package duke.command.command;

import duke.command.Command;
import duke.task.TasksController;
import error.ui.UiException;

/***
 * <p>
 * Command to list all tasks in memory.
 * </p>
 */
public class ListCommand implements Command {
    private TasksController tasksController;

    /***
     * <p>
     * ListCommandConstructor.
     * </p>
     * @param tasksController controller for duke.task list from which tasks are listed.
     */
    public ListCommand(TasksController tasksController) {
        this.tasksController = tasksController;
    }

    /***
     * <p>
     * Display tasks.
     * </p>
     * @return new ListenCommand.
     */
    @Override
    public void execute() throws UiException {
        tasksController.displayAllTasks();
    }
}
