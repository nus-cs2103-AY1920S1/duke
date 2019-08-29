import java.util.ArrayList;

public class FindCommand extends Command {
    String keywordToFind;

    public FindCommand(String keywordToFind) {
        this.keywordToFind = keywordToFind;
    }

    @Override
    boolean isExit() {
        return false;
    }

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
