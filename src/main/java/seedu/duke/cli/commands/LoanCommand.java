package seedu.duke.cli.commands;

import seedu.duke.cli.CommandException;
import seedu.duke.cli.annotations.Argument;
import seedu.duke.cli.annotations.CommandConstructor;
import seedu.duke.tasks.DeadlineTask;
import seedu.duke.tasks.LoanTask;
import seedu.duke.tasks.Task;

import java.time.LocalDateTime;
import java.util.Objects;

public class LoanCommand extends AddCommand {
    private final String description;
    private final LocalDateTime deadline;
    private final int amount;
    private final boolean isLoanOut;

    @CommandConstructor("loanout")
    @CommandConstructor("loanin")
    public LoanCommand(@Argument(isCommandName = true) String commandName,
                       int amount,
                       @Argument(isTrailing = true) String description,
                       @Argument(isTrailing = true, prefix = "/by") LocalDateTime deadline) {
        switch (commandName) {
        case "loanout":
            this.isLoanOut = true;
            break;
        case "loanin":
            this.isLoanOut = false;
            break;
        default:
            throw new RuntimeException("Unexpected command name passed to LoanCommand");
        }
        this.amount = amount;
        this.description = description;
        this.deadline = deadline;
    }

    @Override
    protected Task createTask() throws CommandException {
        if (description == null || description.isBlank()) {
            throw new CommandException("Task description cannot be empty.");
        }

        return new LoanTask(isLoanOut, amount, description, deadline);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LoanCommand that = (LoanCommand) o;
        return amount == that.amount && isLoanOut == that.isLoanOut
                && Objects.equals(description, that.description)
                && Objects.equals(deadline, that.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, deadline, amount, isLoanOut);
    }
}
