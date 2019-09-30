import java.util.ArrayList;

public class FindCommand extends Command {
    
    String keywordToFind;

    /**
     * Constructs a Command to find a specific Task from TaskList.
     *
     * @param keywordToFind the keyword to filter out matching Task from TaskList.
     */
    public FindCommand(String keywordToFind) {
        this.keywordToFind = keywordToFind;
    }

    @Override
    boolean isExit() {
        return false;
    }

    /**
     * Executes an FindCommand given TaskList, UI, Storage.
     *
     * @param tasks the TaskList.
     * @param ui the UI.
     * @param storage the file storage.
     */
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task t : tasks.getTaskList()) {
            if (t.description.contains(keywordToFind)) {
                matchingTasks.add(t);
            }
        }
        ui.printMatches(matchingTasks);
    }
}
