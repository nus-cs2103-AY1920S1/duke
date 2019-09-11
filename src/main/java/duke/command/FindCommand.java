package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

public class FindCommand implements Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public String execute(Storage storage, Ui ui, TaskList tasks) {
        int counter = 1;
        String output = "Here are the matching tasks in your list: \n";
        for (int i = 0; i < tasks.getTasksSize(); i++) {
            if (tasks.getTask(i).getName().contains(keyword)) {
                output = output + counter + ". " + tasks.getTask(i).toString() + "\n";
                counter++;
            }
        }
        return output;
    }
}
