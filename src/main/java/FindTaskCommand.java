import java.io.IOException;
import java.util.List;

public class FindTaskCommand extends Command {
    private String keyword;

    public FindTaskCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        List<Task> matchedTasks = tasks.getMatchedTasks(keyword);
        if (matchedTasks.size() == 0) {
            ui.print("There are no tasks in your list matching your keyword!");
        } else {
            ui.print("Here are the matching tasks in your list:");
            for (int i = 1; i <= matchedTasks.size(); i++) {
                ui.print(String.format("%d. %s", i, matchedTasks.get(i - 1)));
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
