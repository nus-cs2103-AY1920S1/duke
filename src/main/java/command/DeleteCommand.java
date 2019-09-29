package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.VocabularyList;
import task.Task;

import static java.lang.String.format;

/**
 * Command to delete a task.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Command to delete a task.
     *
     * @param input the task number in string.
     * @throws DukeException If the task number is of invalid format.
     */
    public DeleteCommand(String input) throws DukeException {
        try {
            this.index = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid Number Format.");
        }
    }

    @Override
    public String getResponse(TaskList tasklist, Ui ui,
                              Storage storage, VocabularyList vocabularyList) throws DukeException {
        DukeException.checkValidity(index < 1 || index > tasklist.getTaskSize(),
                "Index out of range.");
        assert tasklist.getTaskSize() > 0 : "TaskList should not be empty";
        Task task = tasklist.removeTaskByIndex(index);
        storage.updateData(tasklist);
        return ui.generateResponse("Noted. I've removed this task:",
                format("  %s", task.toString()),
                format("Now you have %d tasks in the list.", tasklist.getTaskSize()));
    }
}
