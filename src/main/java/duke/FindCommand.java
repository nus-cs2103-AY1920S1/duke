package duke;

/**
 * Represents a command to find tasks that contain the specified keyword. A <code>FindCommand</code> object corresponds
 * to an input from user to find tasks e.g., find book
 */
public class FindCommand extends Command {
    private String word;

    FindCommand(String word) {
        this.word = word;
    }

    /**
     * Executes the command, finds tasks that contain the related word.
     * User interface prints out message to user.
     *
     * @param tasks Contains the task list.
     * @param ui Deals with interactions with the user.
     * @param storage Deals with loading tasks from the file and saving tasks in the file.
     * @return String find message to be printed by UI.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder sb = new StringBuilder();
        sb.append(ui.printFindMessage());
        int counter = 0;
        for (Task t: tasks.taskList) {
            if (t.description.contains(word)) {
                counter++;
                sb.append(counter).append(". ").append(t).append("\n");
            }
        }
        return sb.toString();
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
