package duke.command;

import duke.DukeException;

import duke.storage.Storage;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

import duke.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddCommand extends Command {
    private String type;
    private String description;

    private static final String MESSAGE_ADD = "Got it. I've added this task:\n  %s\n"
            + "Now you have %d %s in the list.";

    private static final String ERROR_MISSING_DESCRIPTION = "The description cannot be empty.";
    private static final String ERROR_MISSING_DESCRIPTION_AND_TIME = "The description and time cannot be empty.";
    private static final String ERROR_MISSING_DEADLINE = "The deadline must be present. e.g. task /by Monday";
    private static final String ERROR_MISSING_EVENT_TIME = "The event time must be present. e.g. meeting /at Monday";
    private static final String ERROR_WRONG_DATE_FORMAT  = "The date time provided is in the wrong format. "
            + "Expected d/m/yyyy hh:mm.";

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d/M/uuuu HH:mm");

    /**
     * Constructs an Add command.
     *
     * @param type Type of task to add.
     * @param description Description of task to add.
     */
    public AddCommand(String type, String description) {
        this.type = type;
        this.description = description;
    }

    /**
     * Executes Add command to add a task to the given TaskList.
     *
     * @param tasks Current TaskList.
     * @param storage Current Storage.
     * @throws DukeException If invalid input.
     */
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        Task task;
        switch (type) {
        case "event": {
            if (!description.matches(".+\\s/at\\s.+$")) {
                if (description.length() == 0 || description.matches("^\\s?/at\\s?$")) {
                    throw new DukeException(ERROR_MISSING_DESCRIPTION_AND_TIME);
                }
                if (description.matches("^\\s?/at.*")) {
                    throw new DukeException(ERROR_MISSING_DESCRIPTION);
                }
                if (!description.contains("/at") || description.matches(".*/at\\s?")) {
                    throw new DukeException(ERROR_MISSING_EVENT_TIME);
                }
            }
            String[] desc = description.split(" /at ");
            LocalDateTime time;
            try {
                time = LocalDateTime.parse(desc[1], DATE_TIME_FORMATTER);
            } catch (DateTimeParseException ex) {
                throw new DukeException(ERROR_WRONG_DATE_FORMAT);
            }
            task = new Event(desc[0], time);
            break;
        }
        case "deadline": {
            if (!description.matches(".+\\s/by\\s.+$")) {
                if (description.length() == 0 || description.matches("^\\s?/by\\s?$")) {
                    throw new DukeException(ERROR_MISSING_DESCRIPTION_AND_TIME);
                }
                if (description.matches("^\\s?/by.*")) {
                    throw new DukeException(ERROR_MISSING_DESCRIPTION);
                }
                if (!description.contains("/by") || description.matches(".*/by\\s?")) {
                    throw new DukeException(ERROR_MISSING_DEADLINE);
                }
            }
            String[] desc = description.split(" /by ");
            LocalDateTime time;
            try {
                time = LocalDateTime.parse(desc[1], DATE_TIME_FORMATTER);
            } catch (DateTimeParseException ex) {
                throw new DukeException(ERROR_WRONG_DATE_FORMAT);
            }
            task = new Deadline(desc[0], time);
            break;
        }
        default:
            task = new Todo(description);
        }
        tasks.add(task);
        Ui.printIndented(String.format(MESSAGE_ADD,  task.toString(), tasks.size(),
                tasks.size() != 1 ? "tasks" : "task"));
    }
}
