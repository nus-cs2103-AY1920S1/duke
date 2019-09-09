package duke.commands;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.exceptions.DukeException;
import duke.Parser;

public class DuplicateCommand extends Command {

    private String inputInstruction;

    public DuplicateCommand(String inputString) {
        inputInstruction = inputString;
    }

    /**
     * Overrides execute method to achieve DuplicateCommand.
     * @param currentTaskList taskList containing all the current Task
     * @param ui class that handles all I/O managements
     * @param storage class that handles all file storage and loading
     * @return a String output for the specific Commands
     */
    public String execute(TaskList currentTaskList, Ui ui, Storage storage) {
        return ui.printException(new DukeException("duplicate"));
    }
}