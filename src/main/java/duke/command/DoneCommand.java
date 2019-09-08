package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import static java.lang.Integer.parseInt;

public class DoneCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, String command) {
        try {
            int target = parseInt(command.replaceAll("\\D+", "")) - 1;
            tasks.markDone((target));
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            System.out.println("Error [DoneCommand] 0x0000004: Not a valid number.");
        }
    }
}
