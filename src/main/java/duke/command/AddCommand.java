package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.ui.Ui;

import java.io.IOException;

import static java.lang.System.lineSeparator;
import static java.util.Objects.requireNonNull;

/**
 * Represents a {@link Command} to add a {@link Task}.
 */
public class AddCommand extends Command {

    private final Task toAdd;

    /**
     * Creates a {@link Command} that will create a {@link Task} when executed.
     *
     * @param task the Task to be created
     */
    public AddCommand(Task task) {
        this.toAdd = requireNonNull(task);
    }

    @Override
    public CommandResult execute() throws IOException {
        tasks.add(toAdd);
        String data = toAdd.stringify();
        if (tasks.size() > 1) {
            data = lineSeparator() + data;
        }
        storage.append(data);
        return new CommandResult("Got it. I've added this task:"
                + lineSeparator() + Ui.INDENT + toAdd
                + lineSeparator() + "Now you have " + tasks.size() + " tasks in the list."
        );
    }
}
