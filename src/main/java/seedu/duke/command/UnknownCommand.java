package seedu.duke.command;

import seedu.duke.core.DukeException;
import seedu.duke.statistic.Statistic;
import seedu.duke.storage.Storage;
import seedu.duke.tasklist.TaskList;
import seedu.duke.ui.Ui;

import java.util.Random;

/**
 * Represents the unrecognizable text command.
 */
public class UnknownCommand extends Command {

    /**
     * Default constructor.
     */
    public UnknownCommand() {

    }

    /**
     * Executes the command.
     *
     * @param fullCommand Full String command entered by the User.
     * @param ui User Interface object.
     * @param tasks TaskList object.
     * @param taskStorage Storage object for tasks.
     * @param stat Statistic object.
     * @param statStorage Storage object for stats.
     * @return String sequence to be printed to the User.
     * @throws DukeException Thrown due to completely unrecognizable command from User.
     */
    public String execute(String fullCommand, Ui ui, TaskList tasks, Storage taskStorage, Statistic stat,
                          Storage statStorage) throws DukeException {
        Random rand = new Random();
        int random = rand.nextInt(2);
        String output1 = ":-( I don't understand. Perhaps the archives are incomplete";
        String output2 = "It appears I have misplaced my light sabre. What was it that you said again?";
        String finalOutput = "";

        if (random == 0) {
            finalOutput = output1;
        } else {
            finalOutput = output2;
        }

        throw new DukeException(finalOutput);

    }
}
