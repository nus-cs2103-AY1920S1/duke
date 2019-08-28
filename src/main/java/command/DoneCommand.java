package command;

import task.TaskListController;

import java.util.Optional;

/***
 * <p>
 * Command to mark tasks as done.
 * </p>
 */
public class DoneCommand implements Command {
    private TaskListController taskListController;
    private int completedTaskIndex;

    /***
     * <p>
     * DoneCommand constructor.
     * </p>
     * @param taskListController controller for task list that contains task.
     * @param argument index of task to be marked as done.
     */
    public DoneCommand(TaskListController taskListController, String argument) {
        this.taskListController = taskListController;
        completedTaskIndex = Integer.valueOf(argument) - 1;
    }

    /***
     * <p>
     * Sets task to done.
     * </p>
     * @return new ListenCommand.
     */
    @Override
    public Optional<Command> execute() {
        taskListController.setTaskToDone(completedTaskIndex);

        return Optional.of(new ListenCommand(taskListController));
    }
}
