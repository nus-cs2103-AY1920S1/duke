package seedu.duke.Command;

import seedu.duke.Storage.Storage;
import seedu.duke.TaskList.TaskList;
import seedu.duke.Ui.Ui;
import seedu.duke.TaskList.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FindCommand extends Command{
    private String word;
    private TaskList taskList;

    public FindCommand(String word) {
        this.word = word;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws IOException {
        this.taskList = taskList;
    }

    @Override
    public String getResponse() {
        StringBuilder str = new StringBuilder();
        str.append("Here are the matching tasks in your list:\n");
        for (Task task: taskList.contains(word)) {
            str.append(task.toActionString() + "\n");
        }
        return str.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
