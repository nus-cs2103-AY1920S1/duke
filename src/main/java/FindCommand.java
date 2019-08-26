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
     * @param storage Duke object's Storage object to access file for loading/saving tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        ui.showFindTasksMessage(tasks.findTasks(this.keyWord));
    }

    /**
     * Indicates the exit condition of the running Duke object.
     *
     * @return FindCommand is not the ExitCommand so it returns false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
