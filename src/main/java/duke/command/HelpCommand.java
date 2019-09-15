package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class HelpCommand extends Command {

    @Override
    public String execute(TaskList tasks, Storage storage, String command) {
        return Ui.showHelp();
    }
}
