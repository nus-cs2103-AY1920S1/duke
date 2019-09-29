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
    private List<Integer> idxList = new ArrayList<>();

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
        betterSearch(tasks, partTaskName);
        return getTasks();
    }

    /**
     * print out all the matched task.
     *
     * @return all the matched task in String format, including the task position in txt file.
     */
    private String getTasks() throws TaskNotExistException {
        int i = 0;
        if (myList.size() != 0) {
            Ui.printOutput(" Here are the matching tasks in your list: ");
            StringBuilder temp = new StringBuilder();
            for (Task tk : myList) {
                i++;
                temp.append(Ui.frontSpace).append(" ")
                        .append(i).append(". ").append("[")
                        .append(idxList.get(i - 1)).append("]")
                        .append(tk).append("\n");
            }
            return Ui.frontSpace + " Here are the matching tasks in your list: \n" + temp;
        } else {
            throw new TaskNotExistException("task does not exist");
        }
    }

    /**
     * More flexibility in searching for items, used to store the matched task into myList.
     *
     * @param tasks        The list of tasks that store in txt file.
     * @param partTaskName The part need to match for task name.
     */
    private void betterSearch(TaskList tasks, String partTaskName) {
        List<Task> taskList = tasks.getTaskList();
        int size = taskList.size();
        Task t;
        for (int idx = 0; idx < size; idx++) {
            t = taskList.get(idx);
            if (t.getDescription().contains(partTaskName)) {
                myList.add(t);
                idxList.add(idx + 1);
            }
        }
    }
}