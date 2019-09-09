package duke.commands;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class FindCommand extends Command {

    private String inputInstruction;

    public FindCommand(String inputString) {
        inputInstruction = inputString;
    }

    /**
     * Overrides execute method to achieve FindCommand.
     * @param currentTaskList taskList containing all the current Task
     * @param ui class that handles all I/O managements
     * @param storage class that handles all file storage and loading
     * @return a String output for the specific Commands
     */
    public String execute(TaskList currentTaskList, Ui ui, Storage storage) {
        try {
            return ui.printFind(inputInstruction, currentTaskList);
        } catch (Exception e) {
            return ui.printException(e);
        }
    }
}
