package command;

import task.TaskListController;

import java.util.Optional;

/***
 * <p>
 * Command to delete tasks from memory.
 * </p>
 */
public class DeleteCommand implements Command {
    private TaskListController taskListController;
    private int deletedTaskIndex;

    /***
     * <p>
     * Delete command constructor.
     * </p>
     * @param taskListController controller for task list from which task is deleted.
     * @param argument index of task to be deleted.
     */
    public DeleteCommand(TaskListController taskListController, String argument) {
        this.taskListController = taskListController;
        deletedTaskIndex = Integer.valueOf(argument) - 1;
    }

    /***
     * <p>
     * Deletes task.
     * </p>
     * @return new ListenCommand.
     */
    @Override
    public Optional<Command> execute() {
        taskListController.deleteTask(deletedTaskIndex);

        return Optional.of(new ListenCommand(taskListController));
    }

}
