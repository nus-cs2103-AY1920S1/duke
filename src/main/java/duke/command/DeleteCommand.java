package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Ui;

public class DeleteCommand extends Command {
    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public void execute(TaskList taskList, Ui ui) throws DukeException {
        taskList.deleteTask(this.taskNumber);
    }

    public boolean isExit() {
        return false;
    }
}
