package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class InvalidCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, String command) {
        System.out.println("Invalid Command, try again.");
    }
}
