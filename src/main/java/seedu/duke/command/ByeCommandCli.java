package seedu.duke.command;

import seedu.duke.statistic.Statistic;
import seedu.duke.storage.Storage;
import seedu.duke.tasklist.TaskList;
import seedu.duke.ui.Ui;

import java.io.IOException;

/**
 * Represents the Bye Command for Duke's CLI.
 */
public class ByeCommandCli extends Command {

    /**
     * Default constructor.
     */
    public ByeCommandCli() {

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
     * @throws IOException When writing the saved data to file.
     */
    public String execute(String fullCommand, Ui ui, TaskList tasks, Storage taskStorage, Statistic stats,
                          Storage statStorage) throws IOException {

        stats.incrementTotalCommandsExecuted();

        saveData(tasks, taskStorage, stats, statStorage);

        return ui.getByeSequence();

    }

    /**
     * Saves data of tasks and stats to txt files.
     *
     * @param tasks TaskList of tasks.
     * @param taskStorage Storage object for tasks.
     * @param stats Statistic object.
     * @param statStorage Storage object for stats.
     * @throws IOException Thrown when writing data to file.
     */
    public void saveData(TaskList tasks, Storage taskStorage, Statistic stats,
                         Storage statStorage ) throws IOException {

        taskStorage.clearTaskFileBeforeSaving();


        for (int i = 0; i < tasks.getSize(); i++) {
            taskStorage.writeToTaskFile(tasks.getTask(i).toSaveString());
        }

        statStorage.saveStatFile(stats);

    }
}
