package duke.command;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

/**
 * Represents an executable action Delete. A <code>DeleteCommand</code> object corresponds to
 * a Delete action for a <code>Task</code> object as specified by Duke's user and deletes it from TaskList.
 */
public class DeleteCommand extends Command {

    private String directive;
    private int position;

    public DeleteCommand(String directive, int position) {
        this.directive = directive;
        this.position = position;
    }

    /**
     * Performs operations of type Delete on Task objects and removes them from the list of tasks.
     * Directs action of file storage and user interaction.
     *
     * @param taskList  List of Task objects.
     * @param ui Interface for user interaction.
     * @param storage Interface for read and write operations on file.
     * @throws DukeException  Error while executing command.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = taskList.deleteTaskAtNumber(position);
            ui.notifyTaskDeleted(task, taskList.size());
            storage.writeListToFile(taskList);
        } catch (Exception ex) {
            throw new DukeException(ex.getMessage());
        }
    }

}
