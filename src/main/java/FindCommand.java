import java.util.HashMap;

/**
 * Represents the command of finding tasks with a specified keyword in a Duke object's tasks.
 */
public class FindCommand extends Command {
    protected String keyWord;

    /**
     * Creates a FindCommand object.
     *
     * @param keyWord Keyword to search for in the list of tasks.
     */
    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    /**
     * Executes the search and retrieval of all the Task objects in a Duke object's TaskList object that
     * contains the keyword. The tasks as retrieved as a new TaskList object which is then listed to the user.
     *
     * @param tasks TaskList object to search in.
     * @param ui Duke object's Ui object to display any results from the search.
     * @param taskStorage Duke object's Storage object to access file for loading/saving tasks.
     * @param archiveStorage Duke object's ArchiveStorage object to access archives file for loading/saving archives.
     * @param archives HashMap of all TaskList archives, with their archive name as keys.
     * @return String list of tasks found.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, TaskStorage taskStorage, ArchiveStorage archiveStorage,
                          HashMap<String, TaskList> archives) {
        TaskList tasksWithKeyword = tasks.findTasks(this.keyWord);
        // Postcondition for findTasks method.
        assert tasksWithKeyword != null : "List of tasks returned from search should never be null";
        return ui.showFindTasksMessage(tasksWithKeyword);
    }
}
