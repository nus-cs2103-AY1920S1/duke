package command;

import storage.Storage;
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
        output += iterateTaskList(tasks.getTaskList());

        assert !output.equals("") : "Output should not be empty";

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
