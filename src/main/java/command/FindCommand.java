package command;

import duke.Storage;
import duke.Ui;
import task.Task;
import task.TaskList;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String textToFind;

    public FindCommand(String textToFind) {
        this.textToFind = textToFind;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks.getTasks()) {
            if (task.getDescription().contains(textToFind)) {
                matchingTasks.add(task);
            }
        }

        StringBuilder result = new StringBuilder("");

        if (matchingTasks.size() == 0) {
            result.append("I'm sorry, but I can't find any matching tasks :-(");
        } else {
            result.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                Task currentTask = matchingTasks.get(i);
                result.append("     " + Integer.toString(i + 1) + "." + currentTask.getTypeIcon()
                        + currentTask.getStatusIcon() + " " + currentTask + "\n");
            }
        }

        return result.toString();
    }
}
