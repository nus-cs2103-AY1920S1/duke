package duke;

/**
 * Represents a command to add a tag. A <code>TagCommand</code> object corresponds to
 * an input from user to add a string tag to a task e.g., tag 1 fun
 */
public class TagCommand extends Command {
    private int index;
    private String tag;

    /**
     * Constructor for a tag command.
     *
     * @param index index of the Task to tag to.
     * @param tag word that will be tagged to the Task.
     */
    public TagCommand(int index, String tag) {
        super();
        this.index = index;
        this.tag = tag;
    }

    /**
     * Executes the command, adds tag to the task of specified index.
     * Throws exception if task is not found.
     * User interface prints out message to user.
     *
     * @param tasks Contains the task list.
     * @param ui Deals with interactions with the user.
     * @param storage Deals with loading tasks from the file and saving tasks in the file.
     * @return String tag message to be printed by UI.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index > tasks.getListSize()) {
            throw new DukeException("OOPS!!! Task not found.");
        }
        Task task = tasks.getTask(index);
        task.addTag(tag);
        storage.writeToHardDisk(tasks);
        return ui.printTagMessage(tag, task);
    }

    /**
     * Returns false for exit status.
     * If exit status is false, Duke continues operating.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
