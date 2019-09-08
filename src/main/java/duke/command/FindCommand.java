package duke.command;

import duke.task.TasksController;

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
    FindCommand(TasksController tasksController, String arguments) {
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
    public void execute() {
        tasksController.findTasks(searchParameter);
    }
}
