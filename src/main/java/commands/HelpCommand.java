package commands;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

/**
 * HelpCommand is a class that provides
 * the user with a list of valid commands.
 */
public class HelpCommand extends Command {

    /**
     * Constructor for HelpCommand.
     * Takes in an Array of Strings representing the full command given by the user.
     *
     * @param commandArr String array containing the split text retrieved from user input.
     */
    public HelpCommand(String[] commandArr) {
        super(commandArr);
    }


    /**
     * Returns the String specifying the list of valid commands.
     *
     * @param tasks the TaskList object storing all recorded Tasks.
     * @param ui the Ui object dealing with user interaction.
     * @param storage the Storage object that reads from and writes to the file.
     * @return String output reply from Duke.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.getListOfCommands();
    }

}