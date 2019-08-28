package duke.command;


import duke.component.DukeException;
import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;

import java.io.IOException;

public class DoneCommand extends Command {

    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

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
