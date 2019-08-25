package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;
import exception.DukeInvalidTaskIndexException;

import java.io.IOException;

public class DeleteCommand extends Command {
    private int index;
    public DeleteCommand(int index) {
        this.index = index - 1;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if(tasks.getSize() == 0) {
            throw new DukeException("You have no tasks to delete.");
        } else if (index < 0 || index >= tasks.getSize()) {
            throw new DukeInvalidTaskIndexException("delete", tasks.getSize());
        } else {
            ui.print("Noted. I've removed this task:\n" + tasks.getTask(index) + "\n"
                    + "Now you have " + (tasks.getSize() - 1) + " tasks in the list.");
            tasks.deleteTask(index);
            storage.save(tasks);
        }
    }

    public boolean isExit() {
        return false;
    }

}