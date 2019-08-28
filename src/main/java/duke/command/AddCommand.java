package duke.command;

import duke.component.DukeException;
import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;
import duke.task.Task;

import java.io.IOException;

public class AddCommand extends Command {

    private Task newTask;

    public AddCommand(Task task) {
        this.newTask = task;
    }

    @Override
    public boolean executeCommand(TaskList taskList, Storage storage, Ui ui)
            throws DukeException, IOException {

        taskList.add(newTask);

        ui.printAddedAcknowledgement(newTask, taskList.getSize());

        storage.save(taskList);

        return true;
    }
}
