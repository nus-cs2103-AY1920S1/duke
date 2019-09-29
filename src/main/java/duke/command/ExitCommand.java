package duke.command;

import duke.exception.DukeException;
import duke.ui.Storage;
import duke.ui.TaskList;
import duke.ui.Ui;

/**
 * The ExitCommand used to exit the Duke chatbot.
 */
public class ExitCommand extends Command {
    private String message;

    public ExitCommand() {
        message = " Bye. Hope to see you again soon!\n";
    }

    public ExitCommand(String str) {
        message = str;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert tasks != null;
        assert ui != null;
        assert storage != null;

        return Ui.frontSpace + message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
