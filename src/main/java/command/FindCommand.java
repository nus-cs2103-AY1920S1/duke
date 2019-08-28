package command;

import task.TaskListController;

import java.util.Optional;

/***
 * <p>
 * Command to find tasks.
 * </p>
 */
public class FindCommand implements Command {
    private TaskListController taskListController;
    private String searchParameter;

    /***
     * <p>
     * Finds tasks that contains a particular substring.
     * </p>
     * @param taskListController controller for task list to be searched.
     * @param arguments substring to search for.
     */
    FindCommand(TaskListController taskListController, String arguments) {
        this.taskListController = taskListController;
        searchParameter = arguments;
    }

    /***
     * <p>
     * Search for task.
     * </p>
     * @return new ListenCommand.
     */
    @Override
    public Optional<Command> execute() {
        taskListController.findTasks(searchParameter);
        return Optional.of(new ListenCommand(taskListController));
    }
}
