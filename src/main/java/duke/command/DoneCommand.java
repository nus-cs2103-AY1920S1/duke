package duke.command;

import duke.tasklist.TaskList;
import duke.storage.Storage;
import duke.ui.UI;
import duke.exception.DukeException;

public class DoneCommand extends Command {
    private int index;
    public DoneCommand(String message) {
        super(message);
        this.index = Integer.parseInt(message);
    }

    @Override
    public void execute(TaskList listOfTasks, Storage storage, UI ui) throws Exception {
        if (index > listOfTasks.size() || index <= 0) {
            throw new DukeException("     Such task does not exist!");
        }
        listOfTasks.get(index - 1).completeTask();
        storage.updateTaskList(listOfTasks.getTasks());
        storage.writeToFile();
        ui.printTaskDone(listOfTasks.get(index - 1));
    }
}
