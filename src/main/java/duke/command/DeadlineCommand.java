package duke.command;

import duke.Duke;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Deadline;

import java.text.ParseException;
import java.util.Date;

public class DeadlineCommand extends Command {
    /**
     * Constructs a deadline command.
     *
     * @param params String containing description and deadline.
     */
    public DeadlineCommand(String params) {
        super(params);
    }

    /**
     * Executes the command.
     *
     * @param tasks   Task list containing all tasks.
     * @param storage Storage instance.
     * @return String representation of deadline that was added.
     * @throws DukeException If date parsing fails.
     */
    @Override
    public String executeCommand(TaskList tasks, Storage storage) throws DukeException {
        try {
            String[] tokens = this.params.split(" /by ");
            String description = tokens[0];
            String rawDate = tokens[1];

            Date by = Duke.DATE_FORMATTER.parse(rawDate);

            Deadline newDeadline = new Deadline(description, by);
            tasks.addTask(newDeadline);

            return Ui.showAddedTask(newDeadline);
        } catch (ParseException e) {
            throw new DukeException("Failed to parse date.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Missing date.");
        }
    }
}
