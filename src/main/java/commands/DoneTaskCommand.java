package commands;

import components.Storage;
import components.Ui;
import components.TaskList;

public class DoneTaskCommand implements Command {
    private int index;

    public DoneTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        //IndexOutOfBoundsException is handled in the components.TaskList class
        taskList.markAsDone(index);
    }
}
