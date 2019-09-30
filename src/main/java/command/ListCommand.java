package command;

import task.Task;
import task.TaskList;
import util.Storage;
import util.Ui;

public class ListCommand extends Command {

    public ListCommand(String command) {
        this.inputCommand = command;
    }

    @Override
    public String executeCommand(TaskList taskList, Storage storage) {
        int index = 0;
        StringBuilder sb = new StringBuilder();
        for (Task task : taskList.getTasks()) {
            index++;
            sb.append(index + "." + task.toString() + "\n");
        }
        return Ui.listMsg() + sb.toString();
    }
}
