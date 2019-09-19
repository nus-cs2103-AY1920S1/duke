package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ByeCommand extends Command {
    /**
     * Constructs a bye command.
     */
    public ByeCommand() {
        super();
    }

    /**
     * Executes the command.
     *
     * @return Bye message.
     */
    @Override
    public String executeCommand(TaskList tasks, Storage storage) {
        return Ui.showBye();
    }
}
