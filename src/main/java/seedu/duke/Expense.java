package seedu.duke;

public class Expense {
    protected String description;
    protected double amount;

    /**
     * Class constructor.
     *
     * @param description Description of expense
     * @param amount Amount spent on expense
     */
    public Expense(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }

    /**
     * Returns amount spent on expense.
     * @return Amount
     */
    public double getAmount() {
        return this.amount;
    }

    /**
     * Formats and returns string representation of expense with description and amount.
     *
     * @return String representation of expense
     */
    public String toString() {
        return this.description + ": " + this.amount;
    }

    /**
     * Formats string representation of expense.
     *
     * @return String representation of expense
     */
    public String toWriteIntoFile() {
        return this.description + ": " + this.amount;
    }
}
