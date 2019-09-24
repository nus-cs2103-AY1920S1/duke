package duke.commands;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class ByeCommand extends Command {

    /**
     * Overrides execute method to achieve ByeCommand.
     * @param currentTaskList taskList containing all the current Task
     * @param ui class that handles all I/O managements
     * @param storage class that handles all file storage and loading
     * @return a String output for the ByeCommand
     */
    public String execute(TaskList currentTaskList, Ui ui, Storage storage) {
        return ui.printBye();
    }
}