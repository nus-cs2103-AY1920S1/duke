package duke.commands;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Task;

public class DeadlineCommand extends Command {

    private String inputInstruction;

    public DeadlineCommand(String inputString) {
        inputInstruction = inputString;
    }

    /**
     * Overrides execute method to achieve DeadlineCommand.
     * @param currentTaskList taskList containing all the current Task
     * @param ui class that handles all I/O managements
     * @param storage class that handles all file storage and loading
     * @return a String output for the specific Commands
     */
    public String execute(TaskList currentTaskList, Ui ui, Storage storage) {
        try {
            if (!inputInstruction.contains("/by") || inputInstruction.length() == 8
                    || inputInstruction.length() == 9) {
                throw new DukeException("deadline");
            }
            String subInput1 = inputInstruction.substring(9, inputInstruction.lastIndexOf("/by") - 1);
            String subInput2 = inputInstruction.substring(inputInstruction.lastIndexOf("/by") + 4);
            Task newTask = new Deadline(subInput1, subInput2);
            currentTaskList.addTask(newTask);
            storage.writeToFile(newTask + "\n");
            return ui.printDeadline(newTask, currentTaskList);
        } catch (Exception e) {
            return ui.printException(e);
        }
    }
}
