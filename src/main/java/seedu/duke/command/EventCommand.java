package seedu.duke.command;

import seedu.duke.core.DukeException;
import seedu.duke.parser.Parser;
import seedu.duke.statistic.Statistic;
import seedu.duke.storage.Storage;
import seedu.duke.task.Event;
import seedu.duke.tasklist.TaskList;
import seedu.duke.ui.Ui;

public class EventCommand extends Command {

    public EventCommand() {

    }

    public String execute(String fullCommand, Ui ui, TaskList tasks, Storage taskStorage, Statistic stats,
                          Storage statStorage) throws DukeException {

        stats.incrementTotalCommandsExecuted();

        if ((fullCommand.length() < 6)) {
            // fullCommand contains only the string "event".
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");

        } else if ((fullCommand.lastIndexOf('/') < 1)
                || (4 + fullCommand.lastIndexOf('/') > fullCommand.length()))  {
            // fullCommand does not contain '/' char or there are no chars after "/at".
            throw new DukeException(":-( OOPS!!! The location of an event cannot be empty.");
        }
        String description = Parser.getEventDescription(fullCommand);
        String extraDescription = Parser.getEventLocation(fullCommand);

        Event newEvent = new Event(description, extraDescription);

        tasks.addTask(newEvent);

        return ui.getEventSequence(tasks, newEvent);

    }

}
