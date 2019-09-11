package command;

import task.TaskList;

public class StatisticsCommand extends Command {
    int[] myStats;

        /**
         *
         */

        public String executeCommand(TaskList reference) {
            this.reference = reference;
            myStats= reference.getStats();
            return this.formatOutput();
        }

        /**
         *
         */

        public String formatOutput() {
            return TextFormatter.statsFormat(myStats);

    }

}
