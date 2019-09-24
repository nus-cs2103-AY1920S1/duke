package duke.commands;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.Todo;

public class ListCommand extends Command {

    private String inputInstruction;

    public ListCommand(String inputString) {
        inputInstruction = inputString.toLowerCase();
    }

    /**
     * Overrides execute method to achieve ListCommand.
     * @param currentTaskList taskList containing all the current Task
     * @param ui class that handles all I/O managements
     * @param storage class that handles all file storage and loading
     * @return a String output for the specific Commands
     */
    public String execute(TaskList currentTaskList, Ui ui, Storage storage) {
        try {
            if (!inputInstruction.equals("list")) {
                throw new DukeException("list");
            }
            return ui.printList(currentTaskList);
        } catch (Exception e) {
            return ui.printException(e);
        }
    }
}
