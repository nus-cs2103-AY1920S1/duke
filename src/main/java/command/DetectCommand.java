package command;

import task.Task;
import task.TaskList;
import util.DukeException;
import util.Storage;
import util.Ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DetectCommand extends Command {

    public DetectCommand(String command) {
        this.inputCommand = command;
    }

    @Override
    public void executeCommand(TaskList taskList, Storage storage) {
        assert (!inputCommand.isEmpty()) : "Input inputCommand cannot be empty";
        Set<Task> duplicatesSet = new HashSet<>();
        for (int i = 0; i < taskList.getTasks().size() - 1; i++) {
            for (int j = i + 1; j < taskList.getTasks().size(); j++) {
                if (taskList.getTask(i).getDesc().equals(taskList.getTask(j).getDesc())) {
                    duplicatesSet.add(taskList.getTask(i));
                    duplicatesSet.add(taskList.getTask(j));
                }
            }
        }

        if (duplicatesSet.size() > 0) {
            Ui.duplicateMsg();
            duplicatesSet.stream().forEach(System.out::println);
        } else {
            Ui.duplicateEmptyMsg();
        }

    }
}
