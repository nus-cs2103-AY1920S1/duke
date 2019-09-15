package seedu.duke.commands.base;

import seedu.duke.exceptions.DukeException;
import seedu.duke.tasks.Deadline;
import seedu.duke.util.Storage;
import seedu.duke.util.TaskList;
import seedu.duke.util.UI;

import java.io.IOException;

/**
 * Command to add a Deadline task to the TaskList class.
 */
public class DeadlineCommand extends Command {

    private Deadline deadlineToAdd;

    /**
     * Constructor that primes the deadline to be added to TaskList.
     *
     * @param description Description of deadline.
     * @param dateTime Date and time of deadline.
     * @throws DukeException Throws if dateTime is not of the valid format.
     */
    public DeadlineCommand(String description, String dateTime) throws DukeException {
        deadlineToAdd = new Deadline(description, dateTime);
    }

    /**
     * Adds the deadline to the TaskList, inform the user that it has been done and writes the new change
     * to the text file.
     *
     * @param tasks TaskList of tasks to add the deadline to.
     * @param ui UI to display that deadline has been added.
     * @param storage Storage to write files.
     * @throws DukeException Throws if storage cannot find the file to write to.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        tasks.addToList(deadlineToAdd);
        try {
            storage.writeToFile(tasks);
        } catch (IOException ex) {
            throw new DukeException("OOPS!!! I cannot read your file! :(");
        }

        return ui.operateTask(deadlineToAdd, tasks, true);
    }
}
