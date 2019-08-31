package cs2103t.duke.command;

import cs2103t.duke.file.Storage;
import cs2103t.duke.task.Task;
import cs2103t.duke.task.TaskList;
import cs2103t.duke.ui.Ui;

import java.util.List;

public class FindCommand extends Command {
    private String wordToFind;

    public FindCommand(String keyword) {
        this.wordToFind = keyword;
        super.isExit = false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        List<Task> foundTasks = taskList.findTasks(this.wordToFind);

        if (foundTasks.isEmpty()) {
            ui.dukeRespond("Sorry! I can't find any matching tasks in your list. Try another phrase?");
        } else {
            ui.dukeRespond(getStringArray(foundTasks));
        }
    }

    private String[] getStringArray(List<Task> tasks) {
        String[] strings = new String[tasks.size() + 1];
        strings[0] = "Here are the matching tasks in your list:";
        int listIndex = 0;
        for (Task task : tasks) {
            listIndex++;
            strings[listIndex] = String.format("%d.%s", listIndex, task.toString());
        }
        return strings;
    }
}
