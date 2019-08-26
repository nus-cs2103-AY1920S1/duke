package duke.command.update;

import duke.command.Command;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class MakeDoneCommand extends Command {

    int chosenTaskNo;

    public MakeDoneCommand(int chosenTaskNo) {
        this.chosenTaskNo = chosenTaskNo;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        Task doneTask = tasks.doDoneTask(chosenTaskNo);
        ui.printDoneSuccess(doneTask);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
