package duke.command;

import duke.storage.Storage;
import duke.logic.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Done Command.
 */
public class DoneCommand extends Command {

    private int index;

    /**
     * Constructor for DoneCommand object.
     * @param num is the taskNumber that needs to be done.
     */
    public DoneCommand(int num) {
        super(false);
        this.index = num - 1;

    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.markAsDone(index);

        storage.writeListToFile(taskList);
        return ui.showDoneTask(taskList.getTask(index));
    }
}
