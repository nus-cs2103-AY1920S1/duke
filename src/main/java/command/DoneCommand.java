package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.VocabularyList;

import static java.lang.String.format;

/**
 * Marks a task as done.
 *
 */
public class DoneCommand extends Command {
    private int index;

    /**
     * Marks a task as done.
     *
     * @param input Given user input.
     * @throws DukeException If any error is encountered.
     */
    public DoneCommand(String input) throws DukeException {
        try {
            this.index = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new DukeException("Please ensure that your input is an Integer.");
        }
    }

    @Override
    public String getResponse(TaskList tasklist, Ui ui,
                              Storage storage, VocabularyList vocabularyList) throws DukeException {
        DukeException.checkValidity(index < 1 || index > tasklist.getTaskSize(),
                "Index out of range.");
        tasklist.markDone(index);
        storage.updateData(tasklist);
        return ui.generateResponse("Nice! I've marked this task as done:",
                format("  %s", tasklist.getTaskByIndex(index).toString()));
    }
}


