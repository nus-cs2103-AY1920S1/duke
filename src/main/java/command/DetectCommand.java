package command;

import task.Task;
import task.TaskList;
import util.Storage;
import util.Ui;

import java.util.HashSet;
import java.util.Set;

public class DetectCommand extends Command {

    public DetectCommand(String command) {
        this.inputCommand = command;
    }

    @Override
    public String executeCommand(TaskList taskList, Storage storage) {
        assert (!inputCommand.isEmpty()) : "Input inputCommand cannot be empty";
        String msg;
        Set<Task> duplicatesSet = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < taskList.getTasks().size() - 1; i++) {
            for (int j = i + 1; j < taskList.getTasks().size(); j++) {
                if (taskList.getTask(i).getDesc().equals(taskList.getTask(j).getDesc())) {
                    duplicatesSet.add(taskList.getTask(i));
                    duplicatesSet.add(taskList.getTask(j));
                }
            }
        }

        if (duplicatesSet.size() <= 0) {
            msg = Ui.duplicateEmptyMsg();
        } else {
            msg = Ui.duplicateMsg();
            int index = 0;
            for (Task task : duplicatesSet) {
                index++;
                sb.append(index + "." + task.toString() + "\n");
            }
        }
        return msg + sb.toString();
    }
}
