package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

public class ListCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("     Here are the tasks in your list:");
        int i = 1;
        for (Task task : tasks.getTaskList()) {
            System.out.println("     " + i + ". " + task);
            i++;
        }
    }

    public boolean isExit() {
        return false;
    }

}
