package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * This command gives users a way to find a task by searching for a keyword.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Initialises the keyword to be searched.
     *
     * @param keyword keyword to be searched.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Ensures that Duke application continues to read in user inputs.
     *
     * @return not terminated.
     */
    @Override
    public boolean isTerminated() {
        return false;
    }

    /**
     * Executes the search of a task from the list of tasks.
     * If any task is found to contain the searched keyword string, prints the task out.
     *
     * @param tasklist existing list of tasks.
     * @param ui user interface to print out matching tasks in a new list.
     * @param storage is not used here.
     */
    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) {
        StringBuilder temp = new StringBuilder("Here are the matching tasks in your list: \n");
        for (int i = 0; i < tasklist.size(); i++) {
            String task = tasklist.get(i).toString();
            boolean isMatch = task.contains(keyword);
            if (isMatch) {
                temp.append((i + 1) + "." + task + "\n");
            }
        }
        return temp.toString();
    }

}
