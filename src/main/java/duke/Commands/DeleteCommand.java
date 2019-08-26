package duke.Commands;

import duke.DirectProcessor.TaskList;
import duke.DirectProcessor.Ui;
import duke.DukeException;
import duke.Tasks.Task;

public class DeleteCommand extends Command{

    private int position;

    public DeleteCommand(int position) {
        this.position = position;
    }

    @Override
    public void execute(TaskList tl, Ui ui) throws DukeException{
        if (position > Task.getTotalNumber() || position < 1) {
            throw new DukeException("There is no such task in the list. Please input a valid task number.");
        }
        ui.showDeleteMessage(tl.deleteTask(position));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
