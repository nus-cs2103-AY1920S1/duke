package command;

import main.Storage;
import main.Ui;
import task.InsufficientTaskArgumentException;
import task.TaskList;

public class UndoCommand implements Command {

    public UndoCommand() {

    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) throws InsufficientTaskArgumentException {
        TaskList prevTaskList = storage.undo();
        ui.nextLine("Got it! I've undo the previous command.");
        return prevTaskList;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
