import java.util.List;

/**
 * A Command to find tasks from the task list based on a keyword.
 */
public class FindTaskCommand extends Command {
    private String keyword;

    public FindTaskCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command to find tasks from the task list based on a keyword.
     *
     * @param tasks The task list.
     * @param ui The ui that handles user output.
     * @param storage The storage that handles saving and loading the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matchedTasks = tasks.getMatchedTasks(keyword);
        if (matchedTasks.size() == 0) {
            ui.print("There are no tasks in your list matching your keyword!");
        } else {
            ui.print("Here are the matching tasks in your list:");
            for (int i = 1; i <= matchedTasks.size(); i++) {
                ui.print(String.format("%d. %s", i, matchedTasks.get(i - 1)));
            }
        }
    }

    /**
     * Returns a boolean value signalling whether the program should exit.
     *
     * @return A boolean value indicating whether the program should exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
