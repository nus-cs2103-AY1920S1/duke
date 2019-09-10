package duke.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

public class AddCommand extends Command {
    private String type;
    private String description;

    private static final String MESSAGE_ADD = "Got it. I've added this task:\n  %s\n"
            + "Now you have %d %s in the list.";

    private static final String ERROR_MISSING_DESCRIPTION = "The description cannot be empty.";
    private static final String ERROR_MISSING_DESCRIPTION_AND_DATETIME = "The description and datetime cannot be empty.";
    private static final String ERROR_MISSING_DATETIME = "The datetime must be present. e.g. sleep /by 12/10/2019 12:00";
    private static final String ERROR_WRONG_DATETIME_FORMAT = "The datetime provided is in the wrong format. "
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
     * @param ui Current Ui.
     * @param storage Current Storage.
     * @throws DukeException If invalid input.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task newTask;
        switch (type) {
        case "event":
            newTask = makeEvent();
            break;
        case "deadline":
            newTask = makeDeadline();
            break;
        case "todo":
            newTask = new Todo(description);
            break;
        default:
            throw new DukeException();
        }
        assert newTask != null : "A new task must exist to be added to the TaskList.";
        tasks.add(newTask);

        String pluralizeTasks = tasks.size() != 1 ? "tasks" : "task";
        ui.append(String.format(MESSAGE_ADD,  newTask.toString(), tasks.size(), pluralizeTasks));
    }

    private Task makeEvent() throws DukeException {
        verifyDescriptionFormat("/at");
        String[] desc = description.split(" /at ");
        LocalDateTime time = extractDateTime(desc[1]);
        return new Event(desc[0], time);
    }

    private Task makeDeadline() throws DukeException {
        verifyDescriptionFormat("/by");
        String[] desc = description.split(" /by ");
        LocalDateTime time = extractDateTime(desc[1]);
        return new Deadline(desc[0], time);
    }

    private LocalDateTime extractDateTime(String datetime) throws DukeException {
        LocalDateTime time;
        try {
            time = LocalDateTime.parse(datetime, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException ex) {
            throw new DukeException(ERROR_WRONG_DATETIME_FORMAT);
        }
        return time;
    }

    /**
     * Verify whether description is in proper format. Particularly used in Event and Deadline.
     * @param separator Separator to split description and time.
     * @throws DukeException If input is not in proper format.
     */
    private void verifyDescriptionFormat(String separator) throws DukeException {
        // Check if description has both description and time
        if (!description.matches(String.format(".+\\s%s\\s.+$", separator))) {
            // Check if description and time is missing
            if (description.length() == 0 || description.matches(String.format("^\\s?%s\\s?$", separator))) {
                throw new DukeException(ERROR_MISSING_DESCRIPTION_AND_DATETIME);
            }
            // Check if description is missing
            if (description.matches(String.format("^\\s?%s.*", separator))) {
                throw new DukeException(ERROR_MISSING_DESCRIPTION);
            }
            // Check if time is missing
            if (!description.contains(separator) || description.matches(String.format(".*%s\\s?", separator))) {
                throw new DukeException(ERROR_MISSING_DATETIME);
            }
        }
    }
}
