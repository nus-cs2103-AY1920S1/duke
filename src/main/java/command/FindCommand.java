package command;

import duke.Storage;
import duke.Ui;
import task.Task;
import task.TaskList;

import java.util.ArrayList;

/**
 * Represents a command that finds a task in the task list, which contains keywords that the user inputs.
 */
public class FindCommand extends Command {
    private String textToFind;

    public FindCommand(String textToFind) {
        this.textToFind = textToFind;
    }

    /**
     * Executes this task.
     *
     * @param tasks All the tasks that the user currently has.
     * @param ui The Ui object associated with Duke.
     * @param storage The Storage object associated with Duke
     */
    @Override
    public void  execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks.getTasks()) {
            if (task.getDescription().contains(textToFind)) {
                matchingTasks.add(task);
            }
        }


        if (matchingTasks.size() == 0) {
            ui.showLine();
            ui.println("     I'm sorry, but I can't find any matching tasks :-(");
            ui.showLine();
        } else {
            ui.showLine();
            ui.println("     Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                Task currentTask = matchingTasks.get(i);
                System.out.println("     " + Integer.toString(i + 1) + "." + currentTask.getTypeIcon()
                        + currentTask.getStatusIcon() + " " + currentTask);
            }
            ui.showLine();
        }
    }
}
