package duke.command.command;

import duke.command.Command;
import duke.command.CommandType;
import error.command.CommandCreationException;
import error.ui.UiException;

/***
 * <p>
 * Command to find tasks.
 * </p>
 */
public class FindCommand extends Command {
    private String searchParameter;

    public FindCommand(String arguments) throws CommandCreationException {
        super(CommandType.FIND);
        searchParameter = arguments;
    }

    /***
     * <p>
     * Search for duke.task.
     * </p>
     * @return new ListenCommand.
     */
    @Override
    public void execute() throws UiException {
        tasksController.findTasks(searchParameter);
    }
}
