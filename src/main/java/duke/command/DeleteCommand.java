package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import static java.lang.Integer.parseInt;

public class DeleteCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, String command) {
        try {
            int target = parseInt(command.replaceAll("\\D+", "")) - 1;
            tasks.remove(target);
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            System.out.println("Error [DeleteCommand] 0x0000005: Not a valid number.");
        }
    }
}
