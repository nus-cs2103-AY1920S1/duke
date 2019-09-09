package command;

import main.HistoryManager;
import main.Storage;
import main.Ui;
import task.InsufficientTaskArgumentException;
import task.TaskList;

public class UndoCommand implements Command {

    public UndoCommand() {

    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage, HistoryManager historyManager) throws InsufficientTaskArgumentException {
        TaskList prevTaskList = historyManager.undo();
        storage.updateTasks(prevTaskList);
        ui.nextLine("Got it! I've undo the previous command.");
        return prevTaskList;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
