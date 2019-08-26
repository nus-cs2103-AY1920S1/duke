package duke.Commands;

import duke.DirectProcessor.TaskList;
import duke.DirectProcessor.Ui;
import duke.DukeException;
import duke.Tasks.Task;

public class FinishCommand extends Command {

    private int position;

    public FinishCommand(int position) {
        this.position = position;
    }

    @Override
    public void execute(TaskList tl, Ui ui) throws DukeException{
        if (position > Task.getTotalNumber() || position < 1) {
            throw new DukeException("There is no such task in the list. Please input a valid task number.");
        }
        ui.showFinishMessage(tl.finishTask(position));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
