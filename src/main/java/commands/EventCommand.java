package commands;

import logic.DukeException;
import logic.DukeList;
import logic.DukeStrings;
import logic.Parser;
import logic.Storage;
import logic.Ui;
import task.Events;
import task.Task;

import java.time.LocalDateTime;

/**
 * Encapsulates command to create an Event Task to be added to List of Tasks.
 */
public class EventCommand extends TaskCommands {
    private String args;


    public EventCommand(String args) {
        this.args = args;
    }

    /**
     * Overridden Method to execute the EventCommand.
     *
     * @param list   list of tasks
     * @param ui      User Interface
     * @param storage File Storage and Management
     * @throws DukeException If command arguments is invalid
     */
    @Override
    public void execute(DukeList list, Ui ui, Storage storage) throws DukeException {
        String[] splitStr = args.split("/at");
        if (splitStr[0].trim().isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty");
        } else if (splitStr.length == 1) {
            throw new DukeException(DukeStrings.EVENT_WRONG_FORMAT);
        }

        String[] dateString;
        try {
            dateString = splitStr[1].trim().split(" - "); //e.g. 2/12/2019 1800 - 2/12/2019 1800
            if (dateString.length != 2) {
                throw new DukeException(DukeStrings.EVENT_WRONG_FORMAT);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(DukeStrings.EVENT_WRONG_FORMAT);
        }

        LocalDateTime start = Parser.parseDateTime(dateString[0]);
        LocalDateTime end = Parser.parseDateTime(dateString[1]);

        if (start.isAfter(end)) {
            throw new DukeException(DukeStrings.EVENT_NOT_CHRONO);
        }

        Task task = new Events(false, splitStr[0].trim(), start, end);
        list.add(task);
        storage.updateTaskFile(list);
    }
}
