package command;

import task.TaskListController;

import java.util.Optional;

/***
 * Command to delete tasks from memory.
 */
public class DeleteCommand implements Command {
    private TaskListController taskListController;
    private int deletedTaskIndex;

    /***
     * Delete command constructor.
     * @param taskListController controller for task list from which task is deleted.
     * @param argument index of task to be deleted.
     */
    public DeleteCommand(TaskListController taskListController, String argument) {
        this.taskListController = taskListController;
        deletedTaskIndex = Integer.valueOf(argument) - 1;
    }

    /***
     * Deletes task.
     * @return new ListenCommand.
     */
    @Override
    public Optional<Command> execute() {
        taskListController.deleteTask(deletedTaskIndex);

        return Optional.of(new ListenCommand(taskListController));
    }

}
