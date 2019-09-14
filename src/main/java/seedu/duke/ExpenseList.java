package seedu.duke;

import java.util.ArrayList;

public class ExpenseList {
    protected ArrayList<Expense> arr;
    protected double income;

    /**
     * Class constructor that takes in an array list of expenses.
     *
     * @param arr Arraylist of tasks.
     * @param income User income.
     */
    public ExpenseList(ArrayList<Expense> arr, double income) {
        this.arr = arr;
        this.income = income;
    }

    /**
     * Class constructor that takes in double income.
     *
     * @param income User income.
     */
    public ExpenseList(double income) {
        this.arr = new ArrayList<>();
        this.income = income;
    }

    /**
     * Class constructor.
     */
    public ExpenseList() {
        this.arr = new ArrayList<>();
        this.income = 0.0;
    }

    /**
     * Returns integer number of expenses in list.
     *
     * @return Integer size of the list of expenses.
     */
    public int size() {
        return arr.size();
    }

    /**
     * Returns expense at a specific index in list.
     * The first expense in the list starts with index 0.
     *
     * @param index Index of expense to retrieve from list.
     * @return Expense from list at index.
     */
    public Expense get(int index) {
        return arr.get(index);
    }

    /**
     * Removes the expense at a specific index in list.
     * The first expense in the list starts with index 0.
     *
     * @param index Index of expense to remove from list.
     */
    public void remove(int index) {
        assert index >= 0 : "Invalid task number";
        (this.arr).remove(index);
    }

    /**
     * Adds the expense at the end of list.
     * The first expense in the list starts with index 0.
     *
     * @param expense Expense to be added to the list.
     */
    public void add(Expense expense) {
        (this.arr).add(expense);
    }

    /**
     * Returns sum of all expenditure in array list.
     *
     * @return Double sum of all amounts
     */
    public double getSum() {
        double sum = 0;
        for (int i = 0; i < arr.size(); i++) {
            sum = sum + arr.get(i).getAmount();
        }
        return sum;
    }

    /**
     * Deletes all expenses in this list.
     */
    public void deleteAll() {
        this.arr = new ArrayList<>();
    }

    /**
     * Inputs the user's income.
     *
     * @param income Double value of user income.
     */
    public void inputIncome(double income) {
        this.income = income;
    }

    /**
     * Returns amount of money left after deducting expenditure from income or amount overspent.
     *
     * @return Double value of income left or amount overspent.
     */
    public double getIncomeLeft() {
        if (income >= this.getSum()) {
            return income - this.getSum();
        } else {
            return this.getSum() - income;
        }

    }

    /**
     * Returns if user inputted an income.
     *
     * @return Boolean of whether user inputted an income.
     */
    public boolean isThereInputtedIncome() {
        if (income == 0.0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Return if user has overspent.
     *
     * @return Boolean whether user overspent.
     */
    public boolean isOverspent() {
        return (income - this.getSum() < 0);
    }

}
