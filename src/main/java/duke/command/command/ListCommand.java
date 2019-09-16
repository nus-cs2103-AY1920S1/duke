package duke.command.command;

import duke.command.Command;
import duke.command.CommandType;
import duke.command.UndoAction;
import duke.task.TasksController;
import error.command.CommandCreationException;
import error.ui.UiException;
import ui.UiController;

import java.util.Optional;

/**
 * Command to list all tasks in memory.
 */
public class ListCommand extends Command {
    private static final String INVALID_ARGUMENT_MESSAGE = "☹ OOPS!!! List command doesn't accept arguments! :-(";

    /**
     * Constructor for ListCommand.
     * @param s mandatory argument for command constructors
     * @throws CommandCreationException if argument is not empty
     */
    public ListCommand(String s, UiController ui, TasksController tasksController) throws CommandCreationException {
        super(CommandType.LIST, ui, tasksController);
        if (!s.equals("")) {
            throw new CommandCreationException(INVALID_ARGUMENT_MESSAGE);
        }
    }

    /**
     * Display tasks.
     */
    @Override
    public void execute() throws UiException {
        tasksController.displayAllTasks();
    }

    /**
     * Always returns empty.
     * @return empty optional
     */
    @Override
    public Optional<UndoAction> getUndoAction() {
        return Optional.empty();
    }
}
