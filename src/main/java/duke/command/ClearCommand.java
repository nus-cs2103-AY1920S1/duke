package duke.command;

import duke.*;
import duke.task.Task;

public class ClearCommand extends Command {

    @Override
    public void execute(Duke duke, TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.clear();
        ui.println("Emptied task list");
        storage.saveTaskListToFile(taskList);
    }
}
