package duke.command;

import duke.util.Storage;
import duke.util.TaskList;

import static java.lang.Integer.parseInt;

public class DeleteCommand extends Command {

    /**
     * Executes the Delete Command
     *
     * @param tasks The current tasks loaded
     * @param storage The storage file loaded
     * @param command The command that is being executed
     */
    @Override
    public String execute(TaskList tasks, Storage storage, String command) {
        String message = "";
        try {
            int target = parseInt(command.replaceAll("\\D+", "")) - 1;
            message += tasks.remove(target);
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            return ("Error [DeleteCommand] 0x0000005:" +
                    System.lineSeparator() + "Not a valid number.");
        }
        return message;
    }
}
