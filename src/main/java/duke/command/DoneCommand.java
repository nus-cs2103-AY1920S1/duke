package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class DoneCommand extends Command {

    private int itemNo;

    public DoneCommand(int itemNo) {
        super(false);
        this.itemNo = itemNo;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.done(itemNo, ui);
    }
}
