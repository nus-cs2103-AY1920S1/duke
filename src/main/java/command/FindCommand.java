package command;

import task.Task;
import task.TaskList;
import util.Storage;
import util.Ui;

import java.util.ArrayList;
import java.util.List;

public class FindCommand extends Command {

    public FindCommand(String command) {
        this.inputCommand = command;
    }

    /**
     * Find a task by searching for a keyword.
     * Date and status not included in searching
     *
     * @param taskList the list of tasks
     * @param storage  storage for saving and loading from file
     */
    @Override
    public String executeCommand(TaskList taskList, Storage storage) {
        assert (!inputCommand.isEmpty()) : "Input inputCommand cannot be empty";
        String desc;
        String msg;
        StringBuilder sb = new StringBuilder();
        List<Task> searchList = new ArrayList<>();
        if (inputCommand.substring(4).isEmpty()) {
            return Ui.findErrorMsg();
        } else {
            desc = inputCommand.substring(5);
            for (Task task : taskList.getTasks()) {
                if (task.getDesc().contains(desc)) {
                    searchList.add(task);
                }
            }
        }
        if (searchList.isEmpty()) {
            msg = Ui.findEmptyMsg();
        } else {
            msg = Ui.findMsg();
            int index = 0;
            for (Task task : searchList) {
                index++;
                sb.append(index + "." + task.toString() + "\n");
            }
        }
        return msg + sb.toString();
    }
}
