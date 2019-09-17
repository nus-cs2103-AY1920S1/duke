package seedu.duke.commands;

import seedu.duke.commons.Messages;
import seedu.duke.storage.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.ui.UI;

import java.io.IOException;

/**
 * This class represents the "bye" command by user.
 */
public class ExitCommand  extends Command {

    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) {
        super.isExit = true;
        try {
            storage.save(tasks);
        } catch (IOException e) {
            return Messages.MESSAGE_CANT_WRITE_TO_FILE;
        }
        return Messages.MESSAGE_GOODBYE_MESSAGE;
    }
}
