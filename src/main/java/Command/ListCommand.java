package command;

import task.Task;
import task.TaskList;
import util.Storage;
import util.Ui;

public class ListCommand extends Command {

    public ListCommand(String command) {
        this.command = command;
    }

    @Override
    public void executeCommand(TaskList taskList, Storage storage) {
        int index = 0;
        Ui.listMsg();
        for (Task task : taskList.getTasks()) {
            index++;
            System.out.println(index + "." + task.toString());
        }
    }
}
