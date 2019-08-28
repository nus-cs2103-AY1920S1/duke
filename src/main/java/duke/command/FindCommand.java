package duke.command;

import duke.task.Task;
import duke.task.TaskList;

public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute() {
        TaskList result = tasks.find(keyword);

        ui.showLine();
        if (result.size() == 0) {
            ui.showMessage("There are no matching tasks in your list");
        } else {
            ui.showMessage("Here are the matching tasks in your list:");
            for (int i = 1; i <= result.size(); i++) {
                Task task = result.get(i - 1);
                ui.showMessage(i + ". " + task);
            }
        }
        ui.showLine();
    }
}
