package expense;

public abstract class Expense {
    private String command;
    private double cost;
    private String tagName;
    private int ID;

    /**
     * constructor.
     *
     * @param command is the command
     */
    public Expense(String command) {
        this.command = command;
    }

    /**
     * to change cost.
     *
     * @param newCost is the new price
     */
    public abstract void editExpense(int newCost);

    /**
     * to get cost.
     *
     * @return the cost
     */
    public abstract double getCost();

    /**
     * to return output to be printed.
     */
    @Override
    public abstract String toString();

    public abstract String toSubString();

    public abstract String getTagName();

    public abstract void setID(int x);

    public abstract int getID();
}
