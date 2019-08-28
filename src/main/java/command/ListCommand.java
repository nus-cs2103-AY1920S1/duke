package command;

import task.TaskListController;
import util.DukeOutput;

import java.util.Optional;

/***
 * <p>
 * Command to list all tasks in memory.
 * </p>
 */
public class ListCommand implements Command {
    private TaskListController taskListController;

    /***
     * <p>
     * ListCommandConstructor.
     * </p>
     * @param taskListController controller for task list from which tasks are listed.
     */
    public ListCommand(TaskListController taskListController) {
        this.taskListController = taskListController;
    }

    /***
     * <p>
     * Display tasks.
     * </p>
     * @return new ListenCommand.
     */
    @Override
    public Optional<Command> execute() {
        taskListController.displayAllTasks();

        return Optional.of(new ListenCommand(taskListController));
    }
}
