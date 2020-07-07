package duke.extension.expense;

/**
 * Creaates an expense item for this programme
 */
public class Expense {
    private double amount;
    private String description;

    public Expense(double amount, String description) {
        this.description = description;
        this.amount = amount;
    }

    public double getAmount() {
        return this.amount;
    }
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a string representation of the expense.
     *
     * @return string representation of expense
     */
    @Override
    public String toString() {
       StringBuilder expenseBuilder = new StringBuilder();
       expenseBuilder.append("$ ");
       expenseBuilder.append(String.format("%.2f", amount));
       expenseBuilder.append("\n    ");
       expenseBuilder.append(description);
       return expenseBuilder.toString();
    }
}
