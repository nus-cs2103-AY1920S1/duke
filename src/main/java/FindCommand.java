import java.util.ArrayList;

public class FindCommand extends Command {
    protected String keyword;
    public FindCommand(String findDescription) {
        super("find");
        this.keyword = findDescription;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasksArr = new ArrayList<Task>();
        for (Task currTask : taskList.getTaskArr()) {
            if (currTask.containsKeyword(keyword)) {
                matchingTasksArr.add(currTask);
            }
        }
        if (matchingTasksArr.size() == 0) {
            ui.showMessage("No matching tasks!");
        } else {
            ui.showMatchingKeywordTasks(matchingTasksArr);
        }

    }
}
