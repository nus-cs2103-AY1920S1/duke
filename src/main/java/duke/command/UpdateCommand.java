package duke.command;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

/**
 * Represents an executable action Update. A <code>UpdateCommand</code> object corresponds to
 * a Update action for a <code>Task</code> object status as specified by Duke's user in the TaskList.
 */
public class UpdateCommand extends Command {

    private String directive;
    private int position;

    /**
     * Creates an object of type UpdateCommand to update task information.
     *
     * @param directive Specified type of update action.
     * @param position position of task in a task list.
     */
    public UpdateCommand(String directive, int position) {
        super();
        this.directive = directive;
        this.position = position;
    }

    /**
     * Performs operations of type Update on Task objects and updates them from the list of tasks.
     * Directs action of file storage and user interaction.
     *
     * @param taskList  List of Task objects.
     * @param ui Interface for user interaction.
     * @param storage Interface for read and write operations on file.
     * @throws DukeException  Error while executing command.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = taskList.markNumberedTaskAsDone(position);
        String notification = ui.getNotifyMarkedAsDone(task);
        storage.writeListToFile(taskList);
        return notification;
    }

}