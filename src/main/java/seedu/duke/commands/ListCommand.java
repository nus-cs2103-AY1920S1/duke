package seedu.duke.commands;

import seedu.duke.exceptions.InvalidArgumentException;
import seedu.duke.storage.TaskList;
import seedu.duke.ui.Ui;

/**
 * Abstraction of the List Command.
 * eg: list
 */
public class ListCommand extends Command {

    /**
     * Lists all the tasks in {@code tasks} in a 1-Index based list.
     * @param tasks The current TaskList instance.
     * @throws InvalidArgumentException Thrown when the index {@code i} is out of bounds of {@code tasks}.
     */
    @Override
    public void execute(TaskList tasks) throws InvalidArgumentException {
        String[] message = new String[tasks.size()];

        for (int i = 0; i < tasks.size(); i++) {
            message[i] = "\t" + (i + 1) + "." + tasks.get(i).toString();
        }
        Ui.printMessages(message);
    }
}
