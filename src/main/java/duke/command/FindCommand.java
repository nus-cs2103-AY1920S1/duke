package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

import duke.task.Task;

import java.util.List;
import java.util.ArrayList;

public class FindCommand extends Command {
    String toFind;

    public FindCommand(String toFind) {
        this.toFind = toFind.trim();
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matchingTasks = new ArrayList<>();
        List<Task> searchingList = tasks.getList();
        for (Task task: searchingList) {
            if(task.getName().contains(this.toFind)) {
                matchingTasks.add(task);
            }
        }

        if(matchingTasks.isEmpty()) {
            return("No matching tasks found.");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                sb.append(String.format("  %d.%s\n", i + 1, matchingTasks.get(i)));
            }
            return sb.toString();
        }
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
