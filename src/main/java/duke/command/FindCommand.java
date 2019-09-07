package duke.command;

import duke.exception.DukeException;
import duke.exception.TaskNotExistException;
import duke.task.Task;
import duke.ui.Storage;
import duke.ui.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.List;

/**
 * The command used to find tasks with a keyword.
 */
public class FindCommand extends Command {
    private String[] oneLine;
    private List<Task> myList = new ArrayList<>();

    public FindCommand(String[] oneLine) {
        this.oneLine = oneLine;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert tasks != null;
        assert ui != null;
        assert storage != null;

        String partTaskName = oneLine[1].trim();
        for (Task t : tasks.getTaskList()) {
            if (t.getDescription().contains(partTaskName)) {
                myList.add(t);
            }
        }
        int i = 0;
        if (myList.size() != 0) {
            Ui.printOutput(" Here are the matching tasks in your list: ");
            StringBuilder temp = new StringBuilder();
            for (Task tk : myList) {
                i++;
                temp.append(Ui.frontSpace).append(" ").append(i).append(". ").append(tk).append("\n");
            }
            i = 0;
            return Ui.frontSpace + " Here are the matching tasks in your list: \n" + temp;
        } else {
            throw new TaskNotExistException("task does not exist");
        }
    }
}