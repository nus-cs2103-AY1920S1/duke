package duke.command;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Event;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Represents an executable action Create. A <code>CreateCommand</code> object corresponds to
 * a Create action for a <code>Task</code> object as specified by Duke's user and adds it to TaskList.
 */
public class CreateCommand extends Command {

    private String directive;
    private String description;
    private String addOn;

    public CreateCommand(String directive, String description) {
        this.directive = directive;
        this.description = description;
    }

    /**
     * Creates an object of type CreateCommand with information about task to create.
     *
     * @param directive  Information about task type.
     * @param description Description of specified task.
     * @param addOn Additional information about task.
     */
    public CreateCommand(String directive, String description, String addOn) {
        this.directive = directive;
        this.description = description;
        this.addOn = addOn;
    }

    /**
     * Performs operations of type Create on Task objects and adds them to the list of tasks.
     * Directs action of file storage and user interaction.
     *
     * @param taskList  List of Task objects.
     * @param ui Interface for user interaction.
     * @param storage Interface for read and write operations on file.
     * @throws DukeException  Error while executing command.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        boolean isAdd = true;
        Task task = new Task(description);
        switch (directive) {
        case "todo":
            task = new ToDo(description);
            break;
        case "deadline":
            task = new Deadline(description, addOn);
            break;
        case "event":
            task = new Event(description, addOn);
            break;
        default:
            isAdd = false;
        }
        if (isAdd) {
            taskList.addItem(task);
            ui.notifyTaskAdded(task, taskList.size());
            storage.addTaskToFile(task);
            return;
        }
        throw new DukeException("Error Executing Create Command");
    }
}