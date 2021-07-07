package command;

import core.Storage;
import exception.DukeIllegalArgumentException;
import task.TaskList;
import ui.Ui;

/**
 * RemoveAliasCommand class.
 *
 * <p>Command to remove Aliases.
 *
 * @author Marcus Ong
 */
public class DeleteAliasCommand extends Command {
    private String[] aliasesToDelete;
    private CommandType type;

    /**
     * DeleteAliasCommand Constructor.
     *
     * @param commandString Full command string of user input.
     * @param type CommandType of the command to add aliases for.
     * @param aliasesToDelete Aliases to add for the command.
     */
    public DeleteAliasCommand(String commandString, CommandType type, String...aliasesToDelete) {
        super(CommandType.DELETE_TASK, commandString);
        this.type = type;
        this.aliasesToDelete = aliasesToDelete;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeIllegalArgumentException {
        Command.deleteAlias(type, aliasesToDelete);
        ui.showDeleteAlias(type, aliasesToDelete);
    }
}
