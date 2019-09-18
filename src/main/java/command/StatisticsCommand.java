package command;

import task.TaskList;

    /**
    *<h1> AddCommand</h1>
    * The AddCommand class
    * 1) Instructs the relevant TaskList to add a new task
    * 2) Instructs the Textformatter to return a message for the user
    *
    */

public class StatisticsCommand extends Command {
    int[] myStats;

        /**
         * Checks statistics from the relevant tasklist and return the formatted statistics String
         *
         * @param reference is the tasklist being used by the program
         * @return String the formatted output, after running through formatOutput()
         */

        public String executeCommand(TaskList reference) {
            this.reference = reference;
            myStats= reference.getStats();
            return this.formatOutput();
        }

        /**
         * Returns the formatted command as a formatted string
         *
         *@return String formatted
         */

        public String formatOutput() {
            return TextFormatter.statsFormat(myStats);

    }

}
