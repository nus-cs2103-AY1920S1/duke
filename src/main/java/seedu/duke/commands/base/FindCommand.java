package seedu.duke.commands.base;

import seedu.duke.exceptions.DukeException;
import seedu.duke.tasks.Task;
import seedu.duke.util.Storage;
import seedu.duke.util.TaskList;
import seedu.duke.util.UI;

import java.util.ArrayList;

/**
 * Command to find a certain sequence of characters in the tasks and return the tasks that contain the sequence.
 */
public class FindCommand extends Command {

    private String toFind;

    /**
     * Constructor that takes in the character sequence to find.
     *
     * @param toFind Character sequence to find in the existing TaskList.
     */
    public FindCommand(String toFind) {
        this.toFind = toFind;
    }

    /**
     * Finds all the tasks that contain the toFind variable and outputs them in a TaskList format.
     *
     * @param tasks TaskList of tasks to find the character sequence in.
     * @param ui UI to display found tasks to the user.
     * @param storage Not applicable.
     * @return String that displays found tasks to the user.
     * @throws DukeException Throws if task(s) could not be found.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        ArrayList<Task> temp = new ArrayList<>();
        ArrayList<Task> fullList = tasks.getTaskList();
        for (Task task: fullList) {
            if (task.toString().contains(toFind)) {
                temp.add(task);
            }
        }
        if (temp.size() == 0) {
            throw new DukeException("OOPS!!! I could not find any tasks with these words :-(");
        }
        TaskList temp1 = new TaskList(temp);

        return ui.showFound(temp1);
    }
}
