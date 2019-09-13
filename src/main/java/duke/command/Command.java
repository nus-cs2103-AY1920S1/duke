package duke.command;

import duke.task.TasksController;
import error.command.CommandCreationException;
import error.ui.UiException;
import ui.UiController;

import java.util.Optional;

/**
 * Interface for commands to be executed by the program.
 */
public abstract class Command {
    private CommandType type;
    protected UiController ui;
    protected TasksController tasksController;

    protected Command(CommandType type) throws CommandCreationException {
        this.type = type;
    }

    /**
     * Sets ui interface to be used for I/O.
     * @param ui ui interface
     */
    public void setUi(UiController ui) {
        this.ui = ui;
    }

    /**
     * Sets task controller for operations on tasks.
     * @param controller task controller
     */
    public void setTasksController(TasksController controller) {
        this.tasksController = controller;
    }


    /**
     * Carries out command execution logic.
     * @throws UiException if ui fails unexpectedly
     */
    public abstract void execute() throws UiException;

    /**
     * Returns a functional interface that undoes the actions carried out by this command.
     * @return optional of the functional interface
     */
    public abstract Optional<UndoAction> getUndoAction();

}
