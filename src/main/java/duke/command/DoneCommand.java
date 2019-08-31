package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Ui;

public class DoneCommand extends Command {
    private int taskNumber;

    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public void execute(TaskList taskList, Ui ui) throws DukeException {
        taskList.doTask(this.taskNumber);
    }

    public boolean isExit() {
        return false;
    }
}
