package duke.memo.record.expense;

import duke.memo.exception.DukeException;
import duke.memo.exception.NoAmountException;
import duke.memo.exception.NoDescriptionException;
import duke.memo.exception.NotNumberException;
import duke.memo.record.Record;

public class Expense extends Record {
    private int amount;
    private static final String EXPENSE_TYPE = "expense";

    /**
     * Constructor for Expense.
     * Throw error if there is no amount description or wrong format.
     *
     * @param description  Description for the Expense.
     * @param amount  Amount for the Expense Record.
     * @throws DukeException  If details missing or in wrong format.
     */
    public Expense(String description, String amount) throws DukeException {
        super(description);
        try {
            if (amount.isBlank()) {
                throw new NotNumberException();
            }
            this.amount = Integer.parseInt(amount);
        } catch (NumberFormatException e) {
            throw new NoAmountException(EXPENSE_TYPE);
        }
    }

    /**
     * Constructor for Expense.
     * Throw error if there is no amount description or wrong format.
     *
     * @param desc  Description for the Expense Record.
     * @throws DukeException  If details missing or in wrong format.
     */
    public Expense(String desc) throws DukeException {
        super();
        if (desc.trim().isBlank()) {
            throw new NoDescriptionException(EXPENSE_TYPE);
        }
        try {
            String[] details = desc.split(" / ", 2);
            description = details[0];
            amount = Integer.parseInt(details[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NoAmountException(EXPENSE_TYPE);
        }
    }

    /**
     * Constructor for Expense Record.
     * Throw error if there is no time description or wrong format.
     *
     * @param recordDetails  Details for the Expense Record.
     * @throws DukeException  If details missing or in wrong format.
     */
    public Expense(String[] recordDetails) throws DukeException {
        super();
        try {
            description = recordDetails[1];
            amount = Integer.parseInt(recordDetails[2]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NoAmountException(EXPENSE_TYPE);
        }
    }

    public String getPrintableMsg() {
        return "EX | " + description + " | " + amount;
    }

    @Override
    public String toString() {
        return String.format("[EX] %s (amount: %d)",description,amount);
    }
}
