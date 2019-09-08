package duke.command;

import duke.exception.DukeException;
import duke.task.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * DoneCommands represents user commands to mark a task as done.
 */
public class DoneCommand extends Command {
    private Integer taskNumber;

    /**
     * Constructor for DoneCommand.
     * @param taskNumber index of task to mark as done
     */
    public DoneCommand(Integer taskNumber) {
        super();
        this.taskNumber = taskNumber;
        isExit = false;
    }

    /**
     * Mark a task as done, displays the marked task, then saves the changes.
     * @param tasks a TaskList that stores the list of tasks
     * @param ui Ui object to display messages
     * @param storage Storage object to save changes to
     * @return String that shows what task marked as done
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String message = "";
        Task taskCompleted = tasks.getTask(taskNumber);
        taskCompleted.markAsDone();

        message += ui.showDoneMessage();
        message += ui.showTask(taskCompleted);
        storage.writeTaskToFile(tasks);

        return message;
    }
}
