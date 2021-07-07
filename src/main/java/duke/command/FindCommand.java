package duke.command;

import duke.ListStoreModel;
import duke.Storage;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a find Command. A <code>FindCommand</code> has a user-specified
 * keyword and prints out all the tasks in the task list which contains the
 * keyword, in a readable table.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Prints out all the tasks in the task list which contains the keyword.
     * @param lists taskList and peopleList given.
     * @param ui ui object given.
     * @param storage storage given.
     */
    @Override
    public void execute(ListStoreModel lists, Ui ui, Storage storage) {
        List<Task> tasks = lists.taskList;
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getText().contains(this.keyword)) {
                matchingTasks.add(task);
            }
        }
        if (matchingTasks.isEmpty()) {
            ui.print(String.format("Sorry! There are no tasks that matches '%s'.", this.keyword));
            return;
        }
        ListCommand listCommand = new ListCommand();
        listCommand.execute(new ListStoreModel(matchingTasks), ui, storage);
    }

    /**
     * Overriding equals to compare two FindCommand objects.
     * @param o object to be compared with current FindCommand instance.
     * @return True if the keyword is equivalent.
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof FindCommand && this.keyword.equals(((FindCommand) o).keyword);
    }
}
