package myduke.command;

import myduke.exception.DukeException;
import myduke.exception.DukeInvalidCommandException;
import myduke.storage.StorageManager;
import myduke.task.TaskList;
import myduke.ui.Ui;

public class UpdateAliasCommand extends Command {
    private final CommandParser parser;
    private final String aliasName;
    private final String commandName;

    /**
     * Constructor for Update Alias Command.
     *
     * @param parser    a reference to the current command parser.
     * @param arguments arguments for updating the alias commands.
     *
     * @throws DukeException thrown if the alias contains extra spaces.
     */
    public UpdateAliasCommand(CommandParser parser, String arguments) throws DukeException {
        this.parser = parser;
        String[] args = arguments.split(" ");

        if (args.length > 2) {
            throw new DukeInvalidCommandException("Alias cannot contain spaces.");
        }

        this.aliasName = args[0];
        this.commandName = (args.length == 2) ? args[1] : "";
    }

    @Override
    public void execute(TaskList taskList, Ui ui, StorageManager storage) throws DukeException {

        if (this.commandName.isEmpty()) {
            //remove existing alias
            parser.removeAlias(aliasName);
            ui.printResponse("Alias '" + aliasName + "' was successfully unregistered.");
        } else {

            //update or add alias
            String previousCommandBinding = parser.updateAlias(aliasName, commandName);
            if (previousCommandBinding == null) {
                ui.printResponse("Alias '" + aliasName + "' was successfully registered as '" + commandName + "'");
            } else {
                ui.printResponse(
                    String.format(
                        "Alias '%s' was successfully updated from '%s' to '%s'",
                        aliasName, previousCommandBinding, commandName));
            }
        }
    }
}
