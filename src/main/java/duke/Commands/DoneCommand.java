package duke.Commands;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.Exceptions.DukeException;
import duke.Tasks.Task;

public class DoneCommand extends Command {

    private int index;

    public DoneCommand(int inputIndex) throws DukeException {
        index = inputIndex;
    }

    /**
     * Overrides execute method to achieve DoneCommand.
     * @param currentTaskList taskList containing all the current Task
     * @param ui class that handles all I/O managements
     * @param storage class that handles all file storage and loading
     * @return a String output for the specific Commands
     */
    public String execute(TaskList currentTaskList, Ui ui, Storage storage) {
        try {
            if (currentTaskList.getNoOfTask() < index || index <= 0) {
                throw new DukeException("index");
            }
            Task currentTask = currentTaskList.getTask(index - 1);
            currentTask.markAsDone();
            storage.updateTaskToFile(currentTaskList.getEntireList());
            return ui.printDone(currentTask);
        } catch (Exception e) {
            return ui.printException(e);
        }
    }
}