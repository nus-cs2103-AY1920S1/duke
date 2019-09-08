package duke.command;

import duke.task.TasksController;

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
    public void execute() {
        tasksController.displayAllTasks();
    }
}
