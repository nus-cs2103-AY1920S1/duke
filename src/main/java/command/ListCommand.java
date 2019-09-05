package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Command for listing tasks.
 */
public class ListCommand extends Command {

    /**
     * Execute listing of tasks.
     * @param tasks the TaskList.
     * @param ui the User Interface which responsible for every output printing.
     * @param storage user's hard disk storage.
     * @return Executed output as String.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String output = "";
        output += "Here are the tasks in your list:\n";
        int i = 1;
        for (Task task : tasks.getTaskList()) {
            output += i + ". " + task + "\n";
            i++;
        }
        return output;
    }

    @Override
    /**
     * For JUnit testing purpose.
     */
    public boolean equals(Object obj) {
        return obj instanceof ListCommand;
    }

}
