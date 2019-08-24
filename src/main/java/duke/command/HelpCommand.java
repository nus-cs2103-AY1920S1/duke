package duke.command;

import duke.Duke;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class HelpCommand extends Command {
    @Override
    public void execute(Duke duke, TaskList taskList, Ui ui, Storage storage) {
        ui.printHelp();
    }
}
