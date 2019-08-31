package command;

import command.Command;
import duke.Storage;
import duke.Ui;
import task.Task;
import task.TaskList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
        ui.println("     Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.getTasks().get(i);
            System.out.println("     " + Integer.toString(i + 1) + "." + currentTask.getTypeIcon()
                    + currentTask.getStatusIcon() + " " + currentTask);
        }
        ui.showLine();
        // ui.getUserInput();
    }
}
