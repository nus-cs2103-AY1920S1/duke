package duke.command;

import duke.Ui;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.io.IOException;

public class DoneCommand extends Command {

    int index;

    public DoneCommand(String[] commandArray) {
        String indexString = commandArray[1];
        this.index = Integer.parseInt(indexString);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index < 1 || tasks.getSize() < index) {
            throw new DukeException("☹ OOPS!!! There is no available task in the given index.");
        }
        Task doneTask = tasks.getTask(index - 1);
        doneTask.setDone(true);
        ui.showDoneTask(doneTask);
        try {
            storage.updateFile(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
