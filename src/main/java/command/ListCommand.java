package command;

import task.TaskListController;
import util.DukeOutput;

import java.util.Optional;

/***
 * Command to list all tasks in memory.
 */
public class ListCommand implements Command {
    private TaskListController taskListController;

    /***
     * ListCommandConstructor
     * @param taskListController controller for task list from which tasks are listed.
     */
    public ListCommand(TaskListController taskListController) {
        this.taskListController = taskListController;
    }

    /***
     * Display tasks.
     * @return new ListenCommand.
     */
    @Override
    public Optional<Command> execute() {
        taskListController.displayAllTasks();

        return Optional.of(new ListenCommand(taskListController));
    }
}
