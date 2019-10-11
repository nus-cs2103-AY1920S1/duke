package command;

import task.TaskList;

/**The StatsCommand class.
 * 1) Instructs the tasklist to output the latest stats (Stored as an array)
 *
 */

public class StatisticsCommand extends Command {
    private int[] myStats;

    /**
     * Checks statistics from the relevant tasklist and return the formatted statistics String.
     *
     * @param reference is the tasklist being used by the program
     * @return String the formatted output, after running through formatOutput()
     */

    public String executeCommand(TaskList reference) {
        this.reference = reference;
        myStats = reference.getStats();
        return this.formatOutput();
    }

    /**
     * Returns the formatted command as a formatted string.
     *
     *@return String formatted
     */

    public String formatOutput() {
        return TextFormatter.statsFormat(myStats);
    }

}
