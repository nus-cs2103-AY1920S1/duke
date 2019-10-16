package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.Storage;

import static duke.ui.Message.EXIT_MESSAGE;

public class ExitCommand extends Command {
    public ExitCommand(String[] description) {
        super(description);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return EXIT_MESSAGE;
    }
}
