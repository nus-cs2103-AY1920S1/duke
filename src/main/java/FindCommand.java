import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Represent user command to find tasks using keyword provided.
 */

public class FindCommand extends Command {

    protected String keyword;
    private TaskList tasks = new TaskList();

    /**
     * Represents an action to find specific tasks using keyword provided.
     * @param command Type of task
     * @param keyword Used as keyword for searching.
     */
    public FindCommand(String command, String keyword) {
        super(command);
        this.keyword = keyword;
    }

    public String getKeyword() {
        return this.keyword;
    }

    /**
     * Finds all the tasks that contains the word provided.
     *
     * @param tasks List of tasks.
     * @param ui User Interface.
     * @param storage Storage of tasks.txt files.
     */
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        String word = keyword.trim();
        ArrayList<Task> taskArr = tasks.getTaskArr();
        ArrayList<Task> filteredTasks = taskArr
                                            .stream()
                                            .filter( task -> task.getDescription().contains(word))
                                            .collect(Collectors.toCollection(ArrayList<Task>::new));
        if (filteredTasks.size() == 0) {
            throw new DukeException("Cannot find any tasks that match your keyword.");
        }
        this.tasks = new TaskList(filteredTasks);
    }

    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        StringBuilder printStr = new StringBuilder();
        printStr.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.getTaskCount(); i++) {
            if (i == tasks.getTaskCount() - 1) {
                printStr.append((i + 1) + ". " + tasks.getTask(i));
                break;
            }
            printStr.append((i + 1) + ". " + tasks.getTask(i) + "\n");
        }
        return printStr.toString();
    }
}
