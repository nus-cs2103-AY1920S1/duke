package command;

import core.Storage;
import exception.DukeIllegalArgumentException;
import ui.Ui;
import task.TaskList;

/**
 * ExitCommand class.
 *
 * <p>Command to exit application.
 *
 * @author Marcus Ong
 */
public class ExitCommand extends Command {
    public ExitCommand(String commandString) {
        super(CommandType.EXIT, commandString);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        this.setExit(true);
        ui.showBye();
    }
}
