package duke.command;

import duke.Duke;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Event;

import java.text.ParseException;
import java.util.Date;

public class EventCommand extends Command {
    /**
     * Constructs a event command.
     *
     * @param params String containing description and date.
     */
    public EventCommand(String params) {
        super(params);
    }

    /**
     * Executes the command.
     *
     * @param tasks   Task list containing all tasks.
     * @param storage Storage instance.
     * @return String representation of the event that was added.
     * @throws DukeException If date parsing fails.
     */
    @Override
    public String executeCommand(TaskList tasks, Storage storage) throws DukeException {
        try {
            String[] tokens = this.params.split(" /at ");
            String description = tokens[0];
            String rawDate = tokens[1];

            Date at = Duke.DATE_FORMATTER.parse(rawDate);

            Event newEvent = new Event(description, at);
            tasks.addTask(newEvent);

            return Ui.showAddedTask(newEvent);
        } catch (ParseException e) {
            throw new DukeException("Failed to parse date.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Missing date.");
        }
    }
}
