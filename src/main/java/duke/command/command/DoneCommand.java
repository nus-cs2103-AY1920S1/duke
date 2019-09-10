package duke.command.command;

import duke.command.Command;
import duke.command.CommandType;
import error.command.CommandCreationException;
import error.ui.UiException;

/***
 * <p>
 * Command to mark tasks as done.
 * </p>
 */
public class DoneCommand extends Command {
    private int completedTaskIndex;
    private static final String INVALID_INDEX_MESSAGE = "☹ OOPS!!! PLease enter a valid index! :-(";

    public DoneCommand(String doneIndex) throws CommandCreationException {
        super(CommandType.DONE);
        try {
            completedTaskIndex = Integer.parseInt(doneIndex) - 1;
        } catch (NumberFormatException e) {
            throw new CommandCreationException(INVALID_INDEX_MESSAGE);
        }
    }

    /***
     * <p>
     * Sets duke.task to done.
     * </p>
     * @return new ListenCommand.
     */
    @Override
    public void execute() throws UiException {
        tasksController.setTaskToDone(completedTaskIndex);
    }
}
