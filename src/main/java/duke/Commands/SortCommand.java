package duke.commands;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.exceptions.DukeException;

public class SortCommand extends Command {

    private String inputInstruction;

    public SortCommand(String inputString) {
        inputInstruction = inputString.toLowerCase();
    }

    /**
     * Overrides execute method to achieve SortCommand.
     * @param currentTaskList taskList containing all the current Task
     * @param ui class that handles all I/O managements
     * @param storage class that handles all file storage and loading
     * @return a String output for the specific Commands
     */
    public String execute(TaskList currentTaskList, Ui ui, Storage storage) {
        try {
            if (!inputInstruction.equals("sort")) {
                throw new DukeException("sort");
            }
            currentTaskList.sortTask();
            storage.updateTaskToFile(currentTaskList.getEntireList());
            return ui.printList(currentTaskList);
        } catch (Exception e) {
            return ui.printException(e);
        }
    }
}
