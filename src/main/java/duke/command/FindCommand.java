package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * Represents a command to find a task.
 */
public class FindCommand extends Command {
    /**
     * Term to be searched within the TaskList.
     */
    private String searchTerm;

    /**
     * Constructor for FindCommand.
     *
     * @param searchTerm The term to be searched.
     */
    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    /**
     * Executes the command to search for a keyword.
     * It gets the {@literal arrayList<Task>} from within the TaskList to be able to search,
     * then it loops through each task and searches if the description contains the keyword to be searched.
     * It then creates a new TaskList from all the matches, and then creates a new ListCommand to list out
     * all the tasks that contain the keyword.
     *
     * @param tasks   The TaskList to be searched
     * @param ui      The ui to message the user
     * @param storage Storage if needed
     * @throws DukeException If there is an error while executing the FindCommand
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> taskList = tasks.getTaskArrayList();
        ArrayList<Task> matches = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getTaskDescription().contains(this.searchTerm)) {
                matches.add(task);
            }
        }
        int startNumber = 1;
        ArrayList<String> allTasks = new ArrayList<>();
        for (Task t : new TaskList(matches).getTaskArrayList()) {
            allTasks.add("" + startNumber + "." + t.getTaskStatus());
            startNumber++;
        }
        if (startNumber == 1) {
            allTasks.add("There are no tasks!");
        } else {
            allTasks.add(0, "Here are the tasks found:");
        }
        ui.messageUser(allTasks);
        StringBuilder answer = new StringBuilder();
        for (String message : allTasks) {
            answer.append(message).append('\n');
        }

        return answer.toString();
    }
}
