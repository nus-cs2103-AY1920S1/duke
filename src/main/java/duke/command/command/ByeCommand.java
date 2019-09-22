package duke.command.command;

import duke.command.Command;
import duke.command.entities.CommandType;
import duke.command.entities.UndoAction;
import duke.task.TasksController;
import error.command.CommandCreationException;
import error.ui.UiException;
import ui.Ui;

import java.util.Optional;

/**
 * Command to exit the application.
 */
public class ByeCommand extends Command {
    private static final String INVALID_ARGUMENT_MESSAGE = "☹ OOPS!!! Bye command doesn't accept arguments! :-(";

    /**
     * Constructor for ByeCommand.
     * @param s mandatory argument for command constructors
     * @throws CommandCreationException if argument is not empty
     */
    public ByeCommand(String s, Ui ui, TasksController tasksController) throws CommandCreationException {
        super(CommandType.BYE, ui, tasksController);
        if (!s.equals("")) {
            throw new CommandCreationException(INVALID_ARGUMENT_MESSAGE);
        }
    }

    /**
     * Closes ui interface.
     * @throws UiException if ui fails unexpectedly
     */
    @Override
    public void execute() throws UiException {
        ui.stopUi();
    }

    /**
     * Always returns empty optional.
     * @return empty optional
     */
    @Override
    public Optional<UndoAction> getUndoAction() {
        return Optional.empty();
    }
}
