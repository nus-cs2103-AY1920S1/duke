package command;

import task.TaskListController;

import java.util.Optional;

/***
 * Command to mark tasks as done.
 */
public class DoneCommand implements Command {
    private TaskListController taskListController;
    private int completedTaskIndex;

    /***
     * DoneCommand constructor.
     * @param taskListController controller for task list that contains task.
     * @param argument index of task to be marked as done.
     */
    public DoneCommand(TaskListController taskListController, String argument) {
        this.taskListController = taskListController;
        completedTaskIndex = Integer.valueOf(argument) - 1;
    }

    /***
     * Sets task to done.
     * @return new ListenCommand.
     */
    @Override
    public Optional<Command> execute() {
        taskListController.setTaskToDone(completedTaskIndex);

        return Optional.of(new ListenCommand(taskListController));
    }
}
