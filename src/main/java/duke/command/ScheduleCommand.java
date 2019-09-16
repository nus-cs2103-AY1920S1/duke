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
    String line;

    public ScheduleCommand(String line) throws InvalidParameterException {
        if(line.isBlank()) {
            this.line = line;
        } else if(isValidDate(line)) {
            this.line = line.substring(0, 10);
        } else {
            throw new InvalidParameterException(line);
        }

    }
    /**
     * Executes the command. This will display the list of tasks in the user interface.
     * @param taskManager the list of tasks
     * @param ui the user interface
     * @param storage the storage for the tasks
     */
    public String execute(TaskManager taskManager, UserInterface ui, Storage storage) {
        return ui.showSchedule(taskManager.showSchedule(line));
    }

    private boolean isValidDate(String date) {
        try {
            DateFormatter.format(DateParser.parse(date + " 0000"));
            return true;
        } catch (InvalidDateTimeException idte) {
            return false;
        }
    }

}
