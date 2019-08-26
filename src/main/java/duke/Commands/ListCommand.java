package duke.Commands;

import duke.DirectProcessor.TaskList;
import duke.DirectProcessor.Ui;

public class ListCommand extends Command{

    public ListCommand() {}

    @Override
    public void execute(TaskList tl, Ui ui) {
        ui.showListMessage(tl.listAllTask());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
