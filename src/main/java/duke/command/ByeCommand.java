package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command which shuts down Duke.
 */

public class ByeCommand extends Command{
    /**
     * Constructor for duke.command.ByeCommand
     * @param commandSplitBySpaces String representation of the user input
     */
    public ByeCommand(String[] commandSplitBySpaces){
        super(commandSplitBySpaces);
    }

    /**
     * Executes the command by using the three arguments provided
     * @param taskList
     * @param ui
     * @param storage
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        storage.rewriteData();
        return ui.bye();
    }

    /**
     * Checks if Duke will end.
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
