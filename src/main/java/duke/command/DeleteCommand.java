package duke.command;

import duke.component.DukeException;
import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;
import duke.task.Task;

import java.io.IOException;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean executeCommand(TaskList taskList, Storage storage, Ui ui) throws DukeException, IOException {
        Task deletedTask = taskList.deleteAt(index);

        ui.printRemovedAcknowledgement(deletedTask, taskList.getSize());

        storage.save(taskList);

        return true;
    }
}
