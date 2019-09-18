package seedu.duke.cli.commands;

import seedu.duke.cli.Command;
import seedu.duke.cli.CommandException;
import seedu.duke.cli.annotations.Argument;
import seedu.duke.cli.annotations.CommandConstructor;
import seedu.duke.tasks.Task;

import java.util.List;
import java.util.Objects;

public class FindCommand implements Command {
    private final String substring;

    @CommandConstructor("find")
    public FindCommand(@Argument(isTrailing = true) String substring) {
        this.substring = substring == null ? "" : substring;
    }

    @Override
    public void execute(List<Task> taskList) throws CommandException {
        System.out.println("Here are the matching tasks:");

        String substring = this.substring.toLowerCase();
        for (int i = 0; i < taskList.size(); ++i) {
            Task t = taskList.get(i);
            if (t.getDescription().toLowerCase().contains(substring)) {
                System.out.printf("%d. %s%n", i + 1, t);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FindCommand that = (FindCommand) o;
        return Objects.equals(substring, that.substring);
    }

    @Override
    public int hashCode() {
        return Objects.hash(substring);
    }
}
