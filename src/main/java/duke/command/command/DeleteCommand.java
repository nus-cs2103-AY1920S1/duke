package duke.command.command;

import duke.command.Command;
import duke.command.CommandType;
import error.command.CommandCreationException;
import error.ui.UiException;

public class DeleteCommand extends Command {
    private int deletedTaskIndex;

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
        tasksController.deleteTask(deletedTaskIndex);
    }
}
