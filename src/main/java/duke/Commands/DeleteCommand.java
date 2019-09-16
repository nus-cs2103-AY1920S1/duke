package duke.commands;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.exceptions.DukeException;
import duke.Parser;

public class DeleteCommand extends Command {

    private String inputInstruction;

    public DeleteCommand(String inputString) {
        inputInstruction = inputString;
    }

    /**
     * Overrides execute method to achieve DeleteCommand.
     * @param currentTaskList taskList containing all the current Task
     * @param ui class that handles all I/O managements
     * @param storage class that handles all file storage and loading
     * @return a String output for the specific Commands
     */
    public String execute(TaskList currentTaskList, Ui ui, Storage storage) {
        try {
            if (inputInstruction.length() == 6 || inputInstruction.length() == 7) {
                throw new DukeException("delete");
            }
            int taskNum = Parser.getTaskNum(inputInstruction, ui);
            if (taskNum > currentTaskList.getNoOfTask()) {
                throw new DukeException("index");
            }
            if (taskNum == -1) {
                throw new DukeException("indexInt");
            }
            String outputString = ui.printDelete(taskNum, currentTaskList);
            storage.updateTaskToFile(currentTaskList.getEntireList());
            return outputString;
        } catch (Exception e) {
            return ui.printException(e);
        }
    }
}


