package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.text.ParseException;

/**
 * This class controls the bye command.
 */
public class ByeCommand extends Command {

    public ByeCommand() {
        super.isExit = true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ParseException {
        super.execute(tasks, ui, storage);
        ui.bye();
    }
}