package duke.command;

import duke.exception.FailedToSaveIOException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskManager;
import duke.ui.UserInterface;

import java.io.IOException;

/**
 * The <code>AddCommand</code> is the parent class of {@link AddTodoCommand}, {@link AddDeadlineCommand},
 * {@link AddEventCommand}.
 */
public class AddCommand implements Command {

    /**
     * This is the task to be added by the add command.
     */
    protected Task task;

    /**
     * This is the line contents of the command passed as a parameter.
     */
    protected String line;

    /**
     * Constructs a new add command that will be executed in the <code>run</code> method of {@link duke.main.Duke}
     * with the specified line as a parameter.
     * @param line the line contents of the command passed as a parameter
     */
    public AddCommand(String line) {
        this.line = line;
    }

    /**
     * Executes the command. This will add the specified task into the list of tasks. The task can be a todo, deadline
     * or event item.
     * @param taskManager the list of tasks
     * @param ui the user interface
     * @param storage the storage for the tasks
     */
    public String execute(TaskManager taskManager, UserInterface ui, Storage storage) {
        try {
            taskManager.addToTaskList(task);
            taskManager.addToSchedule(task);
            storage.save(taskManager.getCurrentTaskListToSave());
            return ui.showAddInformation(task.toString(), taskManager.getTaskListSize());
        } catch (FailedToSaveIOException ftsioe) {
            return ui.showSaveError();
        } catch (IOException ioe) {
            return ui.showSaveError();
        }
    }

    protected String getTaskDescription(String[] arr) throws ArrayIndexOutOfBoundsException{
        return arr[0];
    }

    protected String getTaskDate(String[] arr) throws ArrayIndexOutOfBoundsException{
        return arr[1];
    }
}
