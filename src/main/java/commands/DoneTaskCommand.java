package commands;

import components.Storage;
import components.TaskList;

public class DoneTaskCommand implements Command {
    private int index;

    public DoneTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public String[] execute(Storage storage, TaskList taskList) throws DukeException {
        //IndexOutOfBoundsException is thrown as a DukeException in the components.TaskList class
        String[] response = taskList.markAsDone(index);
        storage.save(taskList.getArr());
        return response;
    }
}
