package duke.command;

import duke.exception.DukeException;
import duke.task.Task;

import static java.lang.System.lineSeparator;

/**
 * Represents a {@link Command} to add a {@link Task}.
 */
public class AddCommand extends Command {

    private final Task toAdd;

    public AddCommand(Task task) {
        this.toAdd = task;
    }

    @Override
    public CommandResult execute() throws DukeException {
        tasks.add(toAdd);
        String data = toAdd.stringify();
        if (tasks.size() > 1) {
            data = lineSeparator() + data;
        }
        storage.append(data);
        return new CommandResult("Got it. I've added this task:"
                + lineSeparator() + ui.INDENT + toAdd
                + lineSeparator() + "Now you have " + tasks.size() + " tasks in the list.");
    }
}
