package command;

import command.Command;
import duke.Storage;
import duke.Ui;
import task.Task;
import task.TaskList;

public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder result = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.getTasks().get(i);
            result.append("     " + Integer.toString(i + 1) + "." + currentTask.getTypeIcon()
                    + currentTask.getStatusIcon() + " " + currentTask + "\n");
        }

        return result.toString();
    }
}
