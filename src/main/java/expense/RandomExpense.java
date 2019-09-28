package expense;

public class RandomExpense extends Expense {
    private double cost;
    private String name;
    private String tagName;
    private int id;

    /**
     * Constructor.
     *
     * @param command is the user input
     *
     * @throws Exception if user does not enter number in the proper format
     */
    public RandomExpense(String command) throws Exception {
        super(command);

        String[]nameAndCost = command.split("#", 2);
        try {
            tagName = nameAndCost[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            tagName = "not Tagged";
        }

        String[]parts = nameAndCost[0].split("/for", 2);


        try {
            cost = Double.parseDouble(parts[1]);
        } catch (NumberFormatException e) {
            throw new Exception();
        }
        name = parts[0];

    }

    public void editExpense(int newCost) {
        cost = newCost;
    }

    @Override
    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return name + " | " + cost + " | " + tagName;
    }

    @Override
    public String toSubString() {
        return "(ID: " + id + ") " + name + " | " + cost;
    }

    @Override
    public String getTagName() {
        return tagName;
    }

    @Override
    public void setId(int x) {
        id = x;
    }

    @Override
    public int getId() {
        return id;
    }


}
