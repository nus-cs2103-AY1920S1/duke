package duke.command;

import duke.io.Ui;
import duke.io.Storage;

import duke.task.TaskList;

public class HelpCommand extends Command {
    public HelpCommand(String command) {
        super(command);
    }

    public String execute(TaskList t, Ui ui, Storage storage) {
        return ui.showDetailedHelp();
    }
}
