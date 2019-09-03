package Command;

import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;

import java.io.IOException;

public class ListCommand extends Command {

    public ListCommand() {
    }

    /**
     * execute the command of listing out all the task
     * @param taskList
     * @param ui
     * @param storage
     * @throws IOException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        ui.showListMessage(taskList);
    }

    @Override
    public boolean isExit() {
        return isExit;
    }
}
