package duke.expense;

public class Person {
    private String name;
    private double amountOwed;

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, double amountOwed) {
        this.name = name;
        this.amountOwed = amountOwed;
    }

    /**
     * Returns the name associated with the Person.
     * @return the name associated with the Person.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the amount owed to the Person.
     * @return the amount owed to the Person.
     */
    public double getAmountOwed() {
        return this.amountOwed;
    }

    /**
     * Returns the amount lent to the Person.
     * @return the amount lent to the Person.
     */
    public double getAmountLent() {
        return this.amountOwed * -1;
    }

    /**
     * Receives an amount from the Person.
     * @param amount amount received from the Person.
     */
    public void receive(double amount) {
        this.amountOwed += amount;
    }

    /**
     * Pays an amount to the Person.
     * @param amount amount paid to the Person.
     */
    public void pay(double amount) {
        this.amountOwed -= amount;
    }

    /**
     * Returns true if the Person owes you money.
     * @return true if the Person owes you money.
     */
    public boolean owesYouMoney() {
        return this.amountOwed < 0;
    }

    /**
     * Returns true if the Person lent you money.
     * @return true if the Person lent you money.
     */
    public boolean lentYouMoney() {
        return this.amountOwed > 0;
    }

    /**
     * Returns true if all loans or debts of the Person are settled.
     * @return true if all loans or debts of the Person are settled.
     */
    public boolean hasNoMoreTransactions() {
        return this.amountOwed == 0;
    }

    /**
     * Overriding equals to compare two Person objects.
     * @param o object to be compared with current Person instance.
     * @return True if the name is equivalent.
     */
    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof Person && this.name.equals(((Person) o).name);
    }

    @Override
    public String toString() {
        String verb = amountOwed > 0 ? "owe" : "lent";
        return String.format("You %s %s $%.2f.", verb, name, amountOwed);
    }
}
