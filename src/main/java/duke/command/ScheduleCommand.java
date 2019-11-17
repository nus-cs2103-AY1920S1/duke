package duke.command;

import duke.exception.InvalidDateTimeException;
import duke.exception.InvalidParameterException;
import duke.formatter.DateFormatter;
import duke.parser.DateParser;
import duke.storage.Storage;
import duke.task.TaskManager;
import duke.ui.UserInterface;

/**
 * The <code>ListCommand</code> is created when the user enters <code>"list"</code>. The list command will show the list
 * in a table form for the user when executed on the user interface.
 */
public class ScheduleCommand implements Command {
    /**
     * The date to show the schedule for.
     */
    private String date;

    /**
     * Constructs a new schedule command and displays a schedule for the specified date entered by the user.
     * @param line the date to show the schedule for
     * @throws InvalidParameterException if the information does not exist in the line
     */
    public ScheduleCommand(String line) throws InvalidParameterException {
        if (line.isBlank()) {
            this.date = line;
        } else if (isValidDate(line)) {
            this.date = line.substring(0, 10);
        } else {
            throw new InvalidParameterException(line);
        }

    }

    /**
     * Executes the command. This will display the schedule fo tasks for a specified date in the user interface. If no
     * date is specified by the user, the user interface will display the full schedule for all the tasks without date
     * restriction.
     * @param taskManager the list of tasks
     * @param ui the user interface
     * @param storage the storage for the tasks
     */
    public String execute(TaskManager taskManager, UserInterface ui, Storage storage) {
        return ui.showSchedule(taskManager.showSchedule(date));
    }

    /**
     * Checks whether the string representation of the date passed in is a valid date.
     * @param date the date to check whether is valid
     * @return <code>true</code> if the date is valid and <code>false</code> otherwise
     */
    private boolean isValidDate(String date) {
        try {
            DateFormatter.format(DateParser.parse(date + " 0000"));
            return true;
        } catch (InvalidDateTimeException idte) {
            return false;
        }
    }

}
