package duke.command;

import duke.util.Storage;
import duke.util.TaskList;

import static java.lang.Integer.parseInt;

public class DeleteCommand extends Command {

    @Override
    public String execute(TaskList tasks, Storage storage, String command) {
        String message = String.format("Command: %s%s", command, System.lineSeparator());
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
