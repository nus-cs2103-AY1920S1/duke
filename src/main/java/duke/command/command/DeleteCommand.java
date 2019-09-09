package duke.command.command;

import duke.command.Command;
import duke.task.TasksController;
import error.ui.UiException;

/***
 * <p>
 * Command to delete tasks from memory.
 * </p>
 */
public class DeleteCommand implements Command {
    private TasksController tasksController;
    private int deletedTaskIndex;

    /***
     * <p>
     * Delete duke.command constructor.
     * </p>
     * @param tasksController controller for duke.task list from which duke.task is deleted.
     * @param argument index of duke.task to be deleted.
     */
    public DeleteCommand(TasksController tasksController, String argument) {
        this.tasksController = tasksController;
        deletedTaskIndex = Integer.valueOf(argument) - 1;
    }

    /***
     * <p>
     * Deletes duke.task.
     * </p>
     * @return new ListenCommand.
     */
    @Override
    public void execute() throws UiException {
        tasksController.deleteTask(deletedTaskIndex);
    }

}
