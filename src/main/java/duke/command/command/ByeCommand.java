package duke.command.command;

import duke.command.Command;
import duke.task.TasksController;
import error.ui.UiException;
import ui.UiController;

/***
 * <p>
 * Command to exit the application.
 * </p>
 */
public class ByeCommand implements Command {
    private TasksController tasksController;
    private UiController ui;

    /***
     * <p>
     * ByeCommand constructor.
     * </p>
     * @param tasksController controller for duke.task list to which new tasks will be saved to.
     */
    public ByeCommand(TasksController tasksController, UiController ui) {
        this.tasksController = tasksController;
        this.ui = ui;
    }

    /***
     * <p>
     * Attempts to write new tasks to storage. If unsuccessful, verifies with user if program should close.
     * </p>
     */
    @Override
    public void execute() throws UiException {
        ui.exit();
    }
}
