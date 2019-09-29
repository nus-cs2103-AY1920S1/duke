package duke.command;

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
        StringBuilder eventNameBuilder = new StringBuilder();
        int arrayIndex = 1;
        //creates name of event by appending all words before the substring /at
        while (!commandArray[arrayIndex].equals("/at")) {
            if (arrayIndex >= commandArray.length - 1) {
                throw new DukeException(":( OOPS!!! The '/at' sequence couldn't be found.");
            }
            eventNameBuilder.append(" " + commandArray[arrayIndex]);
            arrayIndex++;
        }
        eventNameBuilder.append(" ");
        arrayIndex++;
        StringBuilder eventDurationBuilder = new StringBuilder();
        //creates duration of event by appending all words after the substring /at
        if (arrayIndex >= commandArray.length) {
            throw new DukeException(":( OOPS!!! The event timing must be specified.");
        }
        boolean isFirstWord = true;
        while (arrayIndex < commandArray.length) {
            if (!isFirstWord) {
                eventDurationBuilder.append(" ");
            }
            eventDurationBuilder.append(commandArray[arrayIndex]);
            arrayIndex++;
            isFirstWord = false;
        }
        String eventDuration = eventDurationBuilder.toString();
        DateTime dateTime = new DateTime(eventDuration);
        String eventName = eventNameBuilder.toString();
        this.event = new Event(eventName, false, dateTime.toString());
    }

    /**
     * Adds an Event object into the TaskList as per the command inputted.
     * 
     * @param tasks   List of Tasks
     * @param storage External storage where the list of tasks is stored
     */

    @Override
    public String execute(TaskList tasks, Storage storage) {
        tasks.addTask(event);
        assert event != null : "event should hold an actual Event object.";
        try {
            storage.writeToFile(event.toFile());
        } catch (IOException e) {
            return "Something went wrong: " + e.getMessage();
        }
        return "Got it. I've added this task:\n"
                + "  " + event + "\n"
                + "Now you have " + tasks.getSize() + " tasks in the list.";
    }
}
