package duke.commands;

import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

public class ExitCommand extends Command {

    public ExitCommand() {
        super();
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage store) {
        return ui.bye();
    }

}
