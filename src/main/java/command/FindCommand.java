package command;

import task.Task;
import task.TaskList;
import util.Storage;
import util.Ui;

import java.util.ArrayList;
import java.util.List;

public class FindCommand extends Command {

    public FindCommand(String command) {
        this.command = command;
    }

    @Override
    public void executeCommand(TaskList taskList, Storage storage) {
        assert (!command.isEmpty()) : "Input command cannot be empty";
        String desc;
        List<Task> searchList = new ArrayList<>();
        if (command.substring(4).isEmpty()) {
            Ui.findErrorMsg();
            return;
        } else {
            desc = command.substring(5);
            for (Task task : taskList.getTasks()) {
                if (task.getDesc().contains(desc)) {
                    searchList.add(task);
                }
            }
        }
        if (searchList.size() <= 0) {
            Ui.findEmptyMsg();
        } else {
            Ui.findMsg();
            int index = 0;
            for (Task task : searchList) {
                index++;
                System.out.println(index + "." + task.toString());
            }
        }
    }
}
