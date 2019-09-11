import java.util.ArrayList;
// TODO change finddscription to accept a arraylist of keywords to match by
public class FindCommand extends Command {
    protected ArrayList<String> keywords;
    public FindCommand(ArrayList<String> keywords) {
        super("find");
        this.keyword = keywords;
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
