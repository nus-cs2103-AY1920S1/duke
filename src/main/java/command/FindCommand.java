package command;

import parser.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Represents a find command
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * @param keyword is the word user wants to search
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Shows all task with description that matches keyword
     *
     * @param tasks contains task list
     * @param ui deals with interaction with users
     * @param storage deals with loading and saving of task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Here are the matching tasks in your list:");
        for (Task task : tasks.getTasks()) {
            int counter = 1;
            if (task.getDescription().indexOf(this.keyword) != -1) {
                System.out.println(counter + "." + task);
            }
        }
    }
}
