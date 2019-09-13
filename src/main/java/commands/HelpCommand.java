package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class HelpCommand extends Command {

    public HelpCommand() {
    }

    /**
     * This method is used to generate the help page for new users.
     *
     * @param tasks   the TaskList object to be used in this command
     * @param ui      the Ui object to be used in this command
     * @param storage the Storage object to be used in this command
     * @return duke opens the help file.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return new Storage("C:\\duke\\src\\main\\java\\data\\help.txt").accessHelp();
    }

}
