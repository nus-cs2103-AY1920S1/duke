package duke.command;

import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;

public class StatsCommand extends Command {

    /**
     * Executes the Statistics Command
     *
     * @param tasks The current tasks loaded
     * @param storage The storage file loaded
     * @param command The command that is being executed
     */
    @Override
    public String execute(TaskList tasks, Storage storage, String command) {
        String message = "";
        try {
            int count = 0;

            for (Task t : tasks.getList()) {
                if (t.getStatusIcon().equals("\u2713")) {
                    count++;
                }
            }

            message += "You have " + count + " complete tasks and " + (tasks.getSize() - count) + " incomplete tasks";
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            return ("Error [StatsCommand] 0x0000013:" +
                    System.lineSeparator() + "Invalid Operation.");
        }
        return message;
    }
}
