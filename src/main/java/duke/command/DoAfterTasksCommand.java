package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidIndexException;
import duke.exception.ListFullException;
import duke.storage.Storage;
import duke.task.DoAfterTasks;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * This command adds a DoAfterTasks to the list of tasks.
 */
public class DoAfterTasksCommand extends Command {

    private String task;
    private String taskIndex;

    /**
     * Initialises the task and prerequisite task's index.
     *
     * @param task name of task.
     * @param taskIndex prerequisite task's index.
     */
    public DoAfterTasksCommand(String task, String taskIndex) {
        this.task = task;
        this.taskIndex = taskIndex;
    }

    /**
     * Ensures that Duke application continues to read in user inputs.
     *
     * @return not terminated.
     */
    @Override
    public boolean isTerminated() {
        return false;
    }

    /**
     * Executes the adding of DoAfterTasks to the list of tasks.
     * Firstly converts the task index string to an integer and checks whether list of tasks
     * is already full (maximum 100 tasks).
     * Then check that the task index entered by the user is valid.
     * Finally creates a new DoAfterTasks to be added to list of tasks and output what has been successfully
     * added using the UI.
     *
     * @param tasklist existing list of tasks.
     * @param ui user interface to inform user what has been done.
     * @param storage is not used here.
     * @throws DukeException if list of tasks is already full or task index is invalid.
     */
    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
        // convert string to int
        int index = Integer.parseInt(taskIndex) - 1;
        if (tasklist.size() >= 100) {
            throw new ListFullException();
        } else if (index < 0 || index >= tasklist.size()) {
            throw new InvalidIndexException();
        } else {
            Task item = tasklist.get(index);
            tasklist.add(new DoAfterTasks(task, item.toString()));
            Task current = tasklist.get(tasklist.size() - 1);
            return "Got it. I've added this task: \n"
                    + "  " + current.toString() + "\n"
                    + String.format("Now you have %d tasks in the list.", tasklist.size());
        }
    }

}
