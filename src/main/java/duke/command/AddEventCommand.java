package duke.command;

import duke.Ui;
import duke.exceptions.DukeException;
import duke.formats.DateTime;
import duke.storage.Storage;
import duke.tasks.Event;
import duke.tasks.TaskList;

import java.io.IOException;

/**
 * Represents the actions to execute when the command 'event' is triggered.
 */

public class AddEventCommand extends Command {

    private Event event;

    /**
     * Returns an AddEventCommand object from the commandArray, an array of words
     * which make up the initial user input.
     * 
     * @param commandArray Array of Strings that form the initial user input
     * @throws DukeException if input doesn't match the format expected
     */

    public AddEventCommand(String[] commandArray) throws DukeException {
        String eventName = "";
        int i = 1;
        while (!commandArray[i].equals("/at")) {
            if (i >= commandArray.length - 1) {
                throw new DukeException("☹ OOPS!!! The '/at' sequence couldn't be found.");
            }
            eventName += " " + commandArray[i];
            i++;
        }
        eventName += " ";
        i++;
        String eventDuration = "";
        if (i >= commandArray.length) {
            throw new DukeException("☹ OOPS!!! The event timing must be specified.");
        }
        boolean isFirstWord = true;
        while (i < commandArray.length) {
            if (!isFirstWord) {
                eventDuration += " ";
            }
            eventDuration += commandArray[i];
            i++;
            isFirstWord = false;
        }
        DateTime dateTime = new DateTime(eventDuration);
        this.event = new Event(eventName, false, dateTime.toString());
    }

    /**
     * Adds an Event object into the TaskList as per the command inputted.
     * 
     * @param tasks   List of Tasks
     * @param ui      User Interface displaying the tasks in the TaskList
     * @param storage External storage where the list of tasks is stored
     */

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(event);
        ui.showAddTask(event, tasks.getSize());
        try {
            storage.writeToFile(event.toFile());
        } catch (IOException e) {
            ui.showIoException(e);
        }
    }
}
