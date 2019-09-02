package duke.command;

import duke.TaskList;
import duke.error.DukeException;
import duke.task.Task;
import duke.util.Storage;
import duke.util.Ui;

public class FindCommand implements Command {
    private String keyword;
    
    /**
     * Constructor.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Check if exit.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Execute.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int counter = 1;
        String strToReturn = ui.prepend4("Here are the matching tasks in your list:");
        System.out.printf("%s", strToReturn);
        for (int i = 1; i <= tasks.getSize(); i++) {
            Task task = tasks.get(i);
            if (task.getName().contains(this.keyword)) {
                String taskInString = ui.prepend4(String.format("%d.%s", counter, task));
                System.out.printf("%s", taskInString);
                strToReturn += taskInString;
                counter++;
            }
        }
        return strToReturn;
    }
}
