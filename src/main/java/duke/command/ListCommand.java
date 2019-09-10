package duke.command;

import duke.task.Task;
import duke.ui.Ui;

import static java.lang.System.lineSeparator;

/**
 * Represents a {@link Command} to display a list of {@link Task}.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays a list of all tasks" + System.lineSeparator()
            + "Usage: " + COMMAND_WORD;

    @Override
    public CommandResult execute() {
        if (tasks.size() == 0) {
            return new CommandResult("There are no tasks in your list");
        }

        StringBuilder feedback = new StringBuilder("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            Task task = tasks.get(i - 1);
            feedback.append(lineSeparator() + Ui.INDENT + i + ". " + task);
        }
        return new CommandResult(feedback.toString());
    }
}
