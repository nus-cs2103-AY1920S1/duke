package command;

import duke.Storage;
import task.Task;
import task.TaskList;
import duke.UserInterface;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs the Find Command object to look for specific tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command and runs the related logic flow.
     * @param tasks List of tasks.
     * @param ui User interface.
     * @param storage Tasks storage: load & save.
     */
    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        ArrayList<Task> taskList = tasks.find(keyword);

        if (taskList.isEmpty()) {
            System.out.println("\tNo tasks matched your description.");
        } else {
            System.out.println("\tHere are the matching tasks in your list:");
            tasks.list(taskList);
        }
    }
}
