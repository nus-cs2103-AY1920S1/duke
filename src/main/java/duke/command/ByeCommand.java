package duke.command;

import duke.Duke;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command which shuts down duke.Duke.
 * @see Duke
 */

public class ByeCommand extends Command{
    /**
     * Constructor for duke.command.ByeCommand
     * @param stringCommand String representation of the user input
     */
    public ByeCommand(String stringCommand){
        super(stringCommand);
    }

    /**
     * Executes the command by using the three arguments provided
     * @param taskList
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.bye();
        storage.rewriteData();
    }

    /**
     * Checks if duke.Duke will end.
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
