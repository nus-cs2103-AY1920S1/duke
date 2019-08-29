package duke.commands;

import duke.exceptions.DukeException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

public class CompleteCommand extends Command {
    int taskNum;

    public CompleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    public void execute(Ui ui, Storage storage, TaskList allTasks) throws DukeException {
        allTasks.completeTask(this.taskNum);
        super.execute(ui, storage, allTasks);
    }
}
