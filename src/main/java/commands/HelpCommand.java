package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class HelpCommand extends Command {

    public HelpCommand() {
    }

    /**
     * This method is used to generate error message.
     *
     * @return duke's response after error
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return new Storage("C:\\duke\\src\\main\\java\\data\\help.txt").accessHelp();
    }

}
