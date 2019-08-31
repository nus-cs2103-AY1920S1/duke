package command;

import exception.DukeException;
import filewriter.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

public class DeleteCommand extends Command {
    int index;

    public DeleteCommand(int index){
        this.index = index;
    }

    public boolean isExit(){
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task removed_Task = tasks.remove(index);
            ui.readDelete(removed_Task, tasks.task_Num);
            storage.editFile(tasks);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new DukeException("Index out of bounds.");
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a single integer for index of task to delete.");
        }
    }
}
