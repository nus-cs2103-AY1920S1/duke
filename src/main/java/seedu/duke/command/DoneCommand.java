package seedu.duke.command;

import seedu.duke.core.DukeException;
import seedu.duke.parser.Parser;
import seedu.duke.statistic.Statistic;
import seedu.duke.storage.Storage;
import seedu.duke.tasklist.TaskList;
import seedu.duke.ui.Ui;

/**
 * Represents the "done" command.
 */
public class DoneCommand extends Command {

    /**
     * Default constructor.
     */
    public DoneCommand() {

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
     * @throws DukeException Thrown when taskNum doesn't exist
     */
    public String execute(String fullCommand, Ui ui, TaskList tasks, Storage taskStorage, Statistic stats,
                          Storage statStorage) throws DukeException {

        stats.incrementTotalCommandsExecuted();

        int taskNum = Parser.getFinishedTaskNum(fullCommand);

        // taskList index (starts from 0) differs from taskNum (starts from 1).
        taskNum--;

        checkInvalidTaskNum(taskNum, tasks);

        tasks.getTask(taskNum).setDone(stats);

        return ui.getDoneSequence(tasks, taskNum);

    }

    /**
     * Checks for invalid task numbers from the User.
     *
     * @param taskNum Int task index to be deleted.
     * @param tasks TaskList of Tasks.
     * @throws DukeException Thrown when taskNum is invalid
     */
    public void checkInvalidTaskNum(int taskNum, TaskList tasks) throws DukeException {
        if (taskNum >= tasks.getSize()) {
            // taskNum does not exist.
            throw new DukeException("Task no. " + (taskNum + 1) + " does not exist");
        }
    }

}
