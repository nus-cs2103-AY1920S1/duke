package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

/**
 * Handles the command to exit from the program and close the bot.
 */
public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            storage.writeToFile(tasks);
            ui.showExit();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
