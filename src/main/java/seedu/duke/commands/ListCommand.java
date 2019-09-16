package seedu.duke.commands;

import seedu.duke.exceptions.InvalidArgumentException;
import seedu.duke.storage.TaskList;
import seedu.duke.trackables.Task;
import seedu.duke.ui.Ui;

import java.util.List;

public class ListCommand extends Command {

    public ListCommand() {
    }

    @Override
    public void execute(TaskList tasks) throws InvalidArgumentException {
        String[] message = new String[tasks.size()];

        for (int i = 0; i < tasks.size(); i++) {
            message[i] = "\t" + (i + 1) + "." + tasks.get(i).toString();
        }
        Ui.printMessages(message);
    }
}
