package commands;

import components.Storage;
import components.Ui;
import tasks.TaskList;

public class DoneTaskCommand implements Command {
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        // InputMismatchException is handled in the Ui class
        int whichTask = ui.readIndex();
        //IndexOutOfBoundsException is handled in the tasks.TaskList class
        taskList.markAsDone(whichTask);
    }
}
