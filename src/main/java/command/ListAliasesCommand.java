package command;

import core.Storage;
import exception.DukeIllegalArgumentException;
import task.TaskList;
import ui.Ui;

/**
 * ListAliasesCommand class.
 *
 * <p>Command to show all aliases.
 *
 * @author Marcus Ong
 */
public class ListAliasesCommand extends Command {

    public ListAliasesCommand(String commandString) {
        super(CommandType.LIST_ALIASES, commandString);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.listAliases();
    }
}
