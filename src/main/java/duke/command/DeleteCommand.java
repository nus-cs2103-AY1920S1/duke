package duke.command;

import duke.tasklist.TaskList;
import duke.storage.Storage;
import duke.ui.UI;
import duke.exception.DukeException;
import duke.task.Task;

public class DeleteCommand extends Command {
    private int indexToDelete;

    public DeleteCommand(String message) {
        super(message);
        this.indexToDelete = Integer.parseInt(message);
    }

    @Override
    public void execute(TaskList listOfTasks, Storage storage, UI ui) throws Exception {
        if (indexToDelete > listOfTasks.size() || indexToDelete <= 0) {
            throw new DukeException("     Such task does not exist!");
        }
        Task toBeDeleted = listOfTasks.get(indexToDelete - 1);
        listOfTasks.removeTask(toBeDeleted);
        storage.updateTaskList(listOfTasks.getTasks());
        storage.writeToFile();
        ui.printTaskDelete(toBeDeleted);
    }

}
