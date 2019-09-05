package duke.command;

import duke.task.Task;
import duke.task.TaskList;

import static java.lang.System.lineSeparator;
import static java.util.Objects.requireNonNull;

public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds and displays a list of Tasks that match the keyword"
            + lineSeparator() + "Usage: " + COMMAND_WORD + " <keyword>";
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = requireNonNull(keyword);
    }

    @Override
    public CommandResult execute() {
        TaskList result = tasks.find(keyword);

        if (result.size() == 0) {
            return new CommandResult("There are no matching tasks in your list");
        } else {
            StringBuilder feedback = new StringBuilder("Here are the matching tasks in your list:");
            for (int i = 1; i <= result.size(); i++) {
                Task task = result.get(i - 1);
                feedback.append(lineSeparator() + i + ". " + task);
            }
            return new CommandResult(feedback.toString());
        }
    }
}
