package duke.command;


import duke.component.DukeException;
import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;

import java.io.IOException;

/**
 * Command Class for changing status of task to completed.
 */
public class DoneCommand extends Command {

    private int index;

    /**
     * Constructor for DoneCommand Object.
     * @param index index of the task whose status will be changed.
     */
    public DoneCommand(int index) {
        this.index = index;
    }


    /**
     * Executes the operation of changing the status of task to be completed.
     * @param taskList list of tasks.
     * @param storage storage to store inside hard disk.
     * @param ui ui for user interaction.
     * @return boolean indication of successful or unsuccessful running of command.
     * @throws DukeException when index is invalid.
     * @throws IOException when error occurs while writing to hard disk.
     */
    @Override
    public boolean executeCommand(TaskList taskList, Storage storage, Ui ui)
            throws DukeException, IOException {

        if (index >= taskList.getSize() || index < 0) {
            throw new DukeException("Invalid task number!");
        }

        taskList.replace(index, taskList.getAtIndex(index).changeToCompletedStatus());

        ui.printDoneAcknowledgement(taskList.getAtIndex(index));

        storage.save(taskList);

        return true;
    }
}
