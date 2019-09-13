package seedu.duke.command;

import seedu.duke.core.DukeException;
import seedu.duke.parser.Parser;
import seedu.duke.statistic.Statistic;
import seedu.duke.storage.Storage;
import seedu.duke.task.Deadline;
import seedu.duke.task.Task;
import seedu.duke.tasklist.TaskList;
import seedu.duke.ui.Ui;

public class DeadlineCommand extends Command {

    public String execute (String fullCommand, Ui ui, TaskList tasks, Storage taskStorage,
                           Statistic stats, Storage statStorage) throws DukeException {

        stats.incrementTotalCommandsExecuted();
        if ((fullCommand.length() < 9)) {

            // fullCommand contains only the string "deadline".
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");

        } else if ((fullCommand.lastIndexOf('/') < 1)
                || (4 + fullCommand.lastIndexOf('/') > fullCommand.length()))  {

            // fullCommand does not contain '/' chars or there are no char after "/by".
            throw new DukeException("☹ OOPS!!! The time period of an event cannot be empty.");

        }

        String description = Parser.getDeadlineDescription(fullCommand);
        String extraDescription = Parser.getDeadlineDateTime(fullCommand);

        assert description != null : "Description should not be null";
        assert extraDescription != null : "Extra Description should not be null";

        Deadline newDeadline = new Deadline(description, extraDescription);

        tasks.addTask(newDeadline);

        return ui.getDeadlineSequence(tasks, newDeadline);
    }
}
