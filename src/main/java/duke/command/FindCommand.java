package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

public class FindCommand implements Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public void execute(Storage storage, Ui ui, TaskList tasks) {
        int counter = 1;
        ui.output("Here are the matching tasks in your list: ");
        for (int i = 0; i < tasks.getTasksSize(); i++) {
            if (tasks.getTask(i).getName().contains(keyword)) {
                ui.output(counter + ". " + tasks.getTask(i).toString());
                counter++;
            }
        }
    }

    public boolean isRunning() {
        return true;
    }
}
