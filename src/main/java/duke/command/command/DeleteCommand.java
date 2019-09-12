package duke.command.command;

import duke.command.Command;
import duke.command.CommandType;
import duke.command.UndoAction;
import duke.task.Task;
import error.command.CommandCreationException;
import error.ui.UiException;

import java.util.Optional;

public class DeleteCommand extends Command {
    private int deletedTaskIndex;
    private Optional<Task> deleted;

    private static final String INVALID_INDEX_MESSAGE = "☹ OOPS!!! PLease enter a valid index! :-(";

    public DeleteCommand(String argument) throws CommandCreationException {
        super(CommandType.DELETE);

        try {
            deletedTaskIndex = Integer.parseInt(argument) - 1;
        } catch (NumberFormatException e) {
            throw new CommandCreationException(INVALID_INDEX_MESSAGE);
        }
    }

    @Override
    public void execute() throws UiException {
        this.deleted = tasksController.deleteTask(deletedTaskIndex);
    }

    @Override
    public Optional<UndoAction> getUndoAction() {
        if (deleted.isEmpty()) {
            return Optional.empty();
        }

        Task restore = deleted.get();
        return Optional.of(() -> tasksController.addTask(restore, false));
    }
}
