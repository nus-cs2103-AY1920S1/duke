import java.lang.StringBuilder;

public class FindCommand extends Command{
    private String word;

    public FindCommand(String word) {
        this.word = word;
    }

    /**
     * Executes the command.
     * Searches for related tasks according to the keyword.
     * @param tasks to handle the tasks
     * @param ui to get user input
     * @param storage to store tasks
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        int count = 1;
        StringBuilder searchResult = new StringBuilder();
        for (Task task : tasks.getWholeList()) {
            if (task.getTask().contains(this.word)) {
                searchResult.append(count).append(".").append(task).append("\n");
                count++;
            }
        }
        return searchResult.toString();
    }

    /**
     * Boolean to exit from program
     * @return true or false depending if we want to stop the program
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
