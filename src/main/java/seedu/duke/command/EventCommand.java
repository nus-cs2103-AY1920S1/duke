package seedu.duke.command;

import seedu.duke.core.DukeException;
import seedu.duke.parser.Parser;
import seedu.duke.statistic.Statistic;
import seedu.duke.storage.Storage;
import seedu.duke.task.Event;
import seedu.duke.tasklist.TaskList;
import seedu.duke.ui.Ui;

/**
 * Represents the "event" command.
 */
public class EventCommand extends Command {

    /**
     * Default constructor.
     */
    public EventCommand() {

    }

    /**
     * Executes the command.
     *
     * @param fullCommand Full String command entered by the User.
     * @param ui User Interface object.
     * @param tasks TaskList object.
     * @param taskStorage Storage object for tasks.
     * @param stats Statistic object.
     * @param statStorage Storage object for stats.
     * @return String sequence to be printed to the User.
     * @throws DukeException Thrown when there is an invalid Deadline command.
     */
    public String execute(String fullCommand, Ui ui, TaskList tasks, Storage taskStorage, Statistic stats,
                          Storage statStorage) throws DukeException {

        stats.incrementTotalCommandsExecuted();

        checkForInvalidInput(fullCommand);

        Event newEvent = newEvent(fullCommand);

        tasks.addTask(newEvent);

        return ui.getEventSequence(tasks, newEvent);

    }

    /**
     * Creates a new Event object from user string.
     *
     * @param fullCommand User input string.
     * @return New Event object.
     */
    public Event newEvent(String fullCommand) throws DukeException  {
        String description = Parser.getEventDescription(fullCommand);
        String extraDescription = Parser.getEventLocation(fullCommand);

        Event newEvent = new Event(description, extraDescription);
        return newEvent;
    }

    /**
     * Checks for invalid input for Deadline command.
     *
     * @param fullCommand String of user input.
     * @throws DukeException Thrown when there is an invalid Deadline command.
     */
    public void checkForInvalidInput(String fullCommand) throws DukeException {

        if ((fullCommand.length() < 6)) {
            // fullCommand contains only the string "event".
            throw new DukeException("A padawan makes many mistakes. The description of an event cannot be empty.");

        } else if ((fullCommand.lastIndexOf('/') < 1)
                || (4 + fullCommand.lastIndexOf('/') > fullCommand.length()))  {
            // fullCommand does not contain '/' char or there are no chars after "/at".
            throw new DukeException("The location of an event cannot be empty. A padawan makes many mistakes");
        }

    }

}
