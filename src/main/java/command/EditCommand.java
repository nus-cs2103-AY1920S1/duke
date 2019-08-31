package command;

import exception.DukeException;
import filewriter.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

public class EditCommand extends Command {
    int index;

    public boolean isExit(){
        return false;
    }

    public EditCommand(int index){
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            if (index == -1){
                throw new IndexOutOfBoundsException();
            }
            Task completedTask = tasks.complete(index);
            ui.readDone(completedTask);
            storage.editFile(tasks);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new DukeException("Index out of bounds.");
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a single integer index of task to delete.");
        }
    }
}
