package duke.command;

import duke.Ui;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.io.IOException;

public class DeleteCommand extends Command {

    int index;

    public DeleteCommand(String[] commandArray){
        String indexString = commandArray[1];
        this.index = Integer.parseInt(indexString);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if(index < 1 || tasks.getSize() < index){
            throw new DukeException("☹ OOPS!!! There is no available task in the given index.");
        }
        Task toRemove = tasks.getTask(index - 1);
        tasks.deleteTask(index - 1);
        ui.showDeleteTask(toRemove, tasks.getSize());
        try {
            storage.updateFile(tasks);
        } catch (IOException e) {
            ui.showIOException(e);
        }
    }
}
