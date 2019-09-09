package command;

import core.Storage;
import exception.DukeIllegalArgumentException;
import task.TaskList;
import ui.Ui;

/**
 * AddAliasCommand class.
 *
 * <p>Command to add Aliases.
 *
 * @author Marcus Ong
 */
public class AddAliasCommand extends Command {
    private String[] aliasesToAdd;
    private CommandType type;

    /**
     * AddAliasCommand Constructor.
     *
     * @param commandString Full command string of user input.
     * @param type CommandType of the command to add aliases for.
     * @param aliasesToAdd Aliases to add for the command.
     */
    public AddAliasCommand(String commandString, CommandType type, String...aliasesToAdd) {
        super(CommandType.ADD_ALIAS, commandString);
        this.type = type;
        this.aliasesToAdd = aliasesToAdd;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeIllegalArgumentException {
        Command.addAlias(type, aliasesToAdd);
        ui.showAddAlias(type, aliasesToAdd);
    }
}
