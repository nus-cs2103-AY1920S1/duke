package seedu.duke.tasks;

import java.time.LocalDateTime;

public class LoanTask extends Task {
    private static final long serialVersionUID = -2528905898387202347L;
    private final boolean isLoanOut;
    private final int amount;
    private final LocalDateTime deadline;

    /**
     * Create a loan task, describing a monetary loan in or out by the user.
     *
     * @param description the task description
     */
    public LoanTask(boolean isLoanOut, int amount, String description, LocalDateTime deadline) {
        super(description);
        this.isLoanOut = isLoanOut;
        this.amount = amount;
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format(
                "[L][%s] ($%d) %s%s", isLoanOut ? "O" : "I",
                amount,
                super.toString(),
                deadline != null
                ? String.format(" (by: %s)", deadline.format(DATE_TIME_FORMATTER))
                : ""
        );
    }
}
