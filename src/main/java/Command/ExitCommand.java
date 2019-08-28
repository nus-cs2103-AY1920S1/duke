package duke.command;

import duke.core.Ui;
import duke.core.Storage;
import duke.helper.DukeException;
import duke.task.TaskList;

public class ExitCommand extends Command {

    public ExitCommand(String inputCommand) {
        super(inputCommand);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        this.shouldExit = true;
        ui.printBye();
    }
}
