package duke.command;

import static duke.ui.SpeechMaker.MESSAGE_TASK_DONE;
import static duke.ui.SpeechMaker.MESSAGE_TASK_UNDONE;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.TextUi;

/**
 * A DoneCommand object contains instructions to mark a task as done or undone.
 */
public class DoneCommand extends Command {

    /**
     * Constructs a DoneCommand with the given details, and a flag to indicate
     * whether the command is intended to mark a task as done or undone.
     *
     * @param details Details of task to be marked as done/undone.
     * @param isDone Whether the task should be marked as done.
     */
    public DoneCommand(String details, boolean isDone) {
        super(details, isDone);
    }

    /**
     * Finds the task specified by the current DoneCommand's details, then marks
     * it as done or undone accordingly.
     *
     * @param tasks List of tasks.
     * @param ui User interface.
     * @param storage Hard disk storage.
     * @return String containing Duke's response.
     * @throws DukeException If task index is invalid, list fails to be saved
     *                       to storage, etc.
     */
    @Override
    public String execute(TaskList tasks, TextUi ui, Storage storage)
            throws DukeException {
        int taskIndex = getTaskIndex(details, tasks.size());
        Task selectedTask = tasks.get(taskIndex);

        String textToDisplay;
        if (isDone) {
            selectedTask.markAsDone();
            textToDisplay = String.format(MESSAGE_TASK_DONE, selectedTask);
        } else {
            selectedTask.markAsUndone();
            textToDisplay = String.format(MESSAGE_TASK_UNDONE, selectedTask);
        }

        ui.showText(textToDisplay);
        try {
            save(tasks, storage);
        } catch (DukeException e) {
            System.err.print(e.getMessage());
        }
        return textToDisplay;
    }
}
