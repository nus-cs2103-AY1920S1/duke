package duke.command;

import java.io.IOException;
import duke.exception.DukeException;
import duke.io.Ui;
import duke.io.Storage;

import duke.task.TaskList;

public class DoneCommand extends Command {
    /** Index to finish task from the TaskList. */
    private int index;

    public DoneCommand(String command, int index) {
        super(command);
        this.index = index;
    }

    /**
     * Finds the task in t, marks it as done and returns the result.
     *
     * @param t the TaskList object
     * @param ui the Ui object
     * @param storage the Storage object
     * @return a String representing the response by duke.ui.Duke from finishing a task
     */
    public String execute(TaskList t, Ui ui, Storage storage) throws DukeException {
        try {
            t.done(index);
            storage.writeToFile(t.asFileFormattedList());
            return ui.asDukeMessage(
                new String[]{"Nice! I've marked this task as done:"},
                new String[]{},
                t.get(index)
            );
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(
                String.format("That task does not exist! You have %d tasks in the list.", t.size())
            );
        } catch (IOException e) {
            throw new DukeException("Saving to file failed.");
        }
    }
}
