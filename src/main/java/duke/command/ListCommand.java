package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * Represents a command to list all the tasks in a TaskList.
 */
public class ListCommand extends Command {

    /**
     * Constructor for ListCommand.
     */
    public ListCommand() {
        super();
    }

    /**
     * Execute the ListCommand.
     * It retrieves the {@literal ArrayList<Task>} from inside the TaskList,
     * and then adds the TaskStatus as a string one by one to a new {@literal ArrayList<String>}.
     *
     * @param tasks   The user's current TaskList
     * @param ui      The ui currently being used by the user
     * @param storage The storage object being used by the user
     * @throws DukeException Exception thrown if there is an error executing the ListCommand
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int startNumber = 1;
        ArrayList<String> allTasks = new ArrayList<>();
        for (Task t : tasks.getTaskArrayList()) {
            allTasks.add("" + startNumber + "." + t.getTaskStatus());
            startNumber++;
        }
        if (startNumber == 1) {
            allTasks.add("There are no tasks!");
        } else {
            allTasks.add(0, "Here are the tasks:");
        }
        ui.messageUser(allTasks);
        String answer = "";
        for (String message : allTasks) {
            answer += message + '\n';
        }

        return answer;
    }
}
