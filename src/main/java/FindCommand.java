import tasks.Task;
import java.util.ArrayList;

/**
 * Represents a deadline command.
 *
 * @author Michelle Yong
 */
public class FindCommand extends Command {
    /**
     * Creates a find command with the description.
     *
     * @param desc The description for the find command.
     */
    public FindCommand(String desc) {
        super(desc);
    }

    /**
     * Executes the find command and shows the task(s) found.
     *
     * @param storage The storage for the file with all the tasks.
     * @param taskList The taskList used.
     * @param ui The User Interface used.
     * @return The task(s) found using the keyword.
     */
    public String execute(Storage storage, TaskList taskList, Ui ui) {
        String toFind = getKeyWord(desc);
        ArrayList<Task> tasks = taskList.getList();
        ArrayList<Task> taskFound = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            assert (i < tasks.size());
            Task t = tasks.get(i);
            if (t.hasKeyword(toFind)) {
                taskFound.add(t);
            }
        }
        return taskList.showTaskFound(taskFound);
    }

    /**
     * Get the keyword to be searched.
     *
     * @param desc The command description.
     * @return The keyword.
     */
    public String getKeyWord(String desc) {
        assert (desc.length() >= 5);
        return desc.substring(5);
    }
}