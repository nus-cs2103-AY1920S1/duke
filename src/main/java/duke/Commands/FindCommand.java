package duke.Commands;

import duke.DirectProcessor.TaskList;
import duke.DirectProcessor.Ui;
import duke.DukeException;

public class FindCommand extends Command{

    private String target;

    public FindCommand(String target) {
        this.target = target;
    }

    @Override
    public void execute(TaskList tl, Ui ui) throws DukeException {
        if (target.equals("")) {
            throw new DukeException("The finding message cannot be empty");
        }
        ui.showFindMessage(tl.listMatchTask(target));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
