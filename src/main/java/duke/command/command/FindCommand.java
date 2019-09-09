package duke.command.command;

import duke.command.Command;
import duke.task.TasksController;
import error.ui.UiException;

/***
 * <p>
 * Command to find tasks.
 * </p>
 */
public class FindCommand implements Command {
    private TasksController tasksController;
    private String searchParameter;

    /***
     * <p>
     * Finds tasks that contains a particular substring.
     * </p>
     * @param tasksController controller for duke.task list to be searched.
     * @param arguments substring to search for.
     */
    public FindCommand(TasksController tasksController, String arguments) {
        this.tasksController = tasksController;
        searchParameter = arguments;
    }

    /***
     * <p>
     * Search for duke.task.
     * </p>
     * @return new ListenCommand.
     */
    @Override
    public void execute() throws UiException {
        tasksController.findTasks(searchParameter);
    }
}
