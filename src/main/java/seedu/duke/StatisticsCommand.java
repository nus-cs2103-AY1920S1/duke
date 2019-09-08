package seedu.duke;

import java.io.IOException;

/**
 * Represents a Statistics Command.
 * A <code>StatisticsCommand</code> object corresponds to a command with a description that consists of "show stats".
 */
public class StatisticsCommand extends Command {

    /**
     * Constructor of the StatisticsCommand class.
     */
    public StatisticsCommand() {
    }

    /**
     * Marked the task as done and updates the status icon of the task.
     * Updates the status of the task in the datafile.
     *
     * @param list the TaskList object that is handling the arraylist of the datafile
     * @param ui the UserInterface object that handles the interaction with users
     * @param storage the Storage object that stores and handles the path to datafile
     * @return the output string
     * @throws IOException on input error
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws IOException {
        list.calculateStats();
        String output = ui.showStatsMsg() + "Number of Tasks Done: " + list.getNumTaskDone() + "\n"
                + "Number of Tasks Not Done: " + list.getNumTaskNotDone() + "\n";
        return output;
    }
}
