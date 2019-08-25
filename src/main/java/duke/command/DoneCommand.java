package duke.command;

import duke.logic.Storage;
import duke.logic.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class DoneCommand extends Command {

    private int index;

    public DoneCommand(int num) {
        super(false);
        this.index = num-1;

    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.markAsDone(index);
        ui.showDoneTask(taskList.getTask(index));
        storage.writeListToFile(taskList);
    }
}
