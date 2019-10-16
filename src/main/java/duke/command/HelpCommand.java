package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.Storage;

import static duke.ui.Message.HELP_MESSAGE;

public class HelpCommand extends Command {
    public HelpCommand(String[] description) {
        super(description);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return HELP_MESSAGE;
    }
}
