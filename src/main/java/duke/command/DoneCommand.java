package duke.command;

import duke.util.Storage;
import duke.util.TaskList;

import static java.lang.Integer.parseInt;

public class DoneCommand extends Command {

    @Override
    public String execute(TaskList tasks, Storage storage, String command) {
        String message = "";
        try {
            int target = parseInt(command.replaceAll("\\D+", "")) - 1;
            message += tasks.markDone((target));
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            return ("Error [DoneCommand] 0x0000004:" +
                    System.lineSeparator() + "Not a valid number.");
        }
        return message;
    }
}
