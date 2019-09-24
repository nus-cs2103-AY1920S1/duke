package duke.command;

import duke.exception.DukeException;
import duke.exception.ListFullException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * This command adds a Todo to the list of tasks.
 */
public class AddTodoCommand extends Command {

    private String item;

    /**
     * Initialises the task to be added.
     *
     * @param item name of task.
     */
    public AddTodoCommand(String item) {
        this.item = item;
    }

    /**
     * Ensures that Duke application continues to read in user inputs.
     *
     * @return not terminated.
     */
    public boolean isTerminated() {
        return false;
    }

    /**
     * Executes the adding of Todo to the list of tasks.
     * Firstly checks whether list of tasks is already full (maximum 100 tasks).
     * Then creates a new Todo to be added to list of tasks.
     * Finally outputs what has been added successfully to the list of tasks using the UI.
     *
     * @param tasklist existing list of tasks.
     * @param ui user interface to inform user what has been added.
     * @param storage is not used here.
     * @throws DukeException if list of tasks is full.
     */
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
        if (tasklist.size() >= 100) {
            throw new ListFullException();
        } else {
            tasklist.add(new Todo(item));
            Task thing = tasklist.get(tasklist.size() - 1);
            ui.sendMessage("Got it. I've added this task: ");
            ui.sendMessage("  " + thing.toString());
            ui.sendMessage(String.format("Now you have %d tasks in the list.", tasklist.size()));
        }
    }

}
