package duke.extension.expense;

import java.util.ArrayList;

public class ExpenseList {
    private ArrayList<ExpenseCategory> expenseCategories;

    public ExpenseList() {
        expenseCategories = new ArrayList<>();
        expenseCategories.add(new ExpenseCategory("Food", new ArrayList<Expense>()));
        expenseCategories.add(new ExpenseCategory("Transport", new ArrayList<Expense>()));
        expenseCategories.add(new ExpenseCategory("Education", new ArrayList<Expense>()));
        expenseCategories.add(new ExpenseCategory("Household", new ArrayList<Expense>()));
        expenseCategories.add(new ExpenseCategory("Apparel", new ArrayList<Expense>()));
        expenseCategories.add(new ExpenseCategory("Beauty", new ArrayList<Expense>()));
        expenseCategories.add(new ExpenseCategory("Health", new ArrayList<Expense>()));
        expenseCategories.add(new ExpenseCategory("Social", new ArrayList<Expense>()));
        expenseCategories.add(new ExpenseCategory("Others", new ArrayList<Expense>()));
    }

    public ArrayList<ExpenseCategory> getExpenseCategories() {
        return this.expenseCategories;
    }

    public void addExpense(String expenseType, Expense newExpense) throws IllegalArgumentException {
        for (ExpenseCategory category : expenseCategories) {
            if (expenseType.toLowerCase().equals(category.getCategoryName().toLowerCase())) {
                category.add(newExpense);
                break;
            }
            if (category.equals("others")) {
                throw new IllegalArgumentException("OOPS, this is not an expense category.");
            }
        }
    }

    public Expense deleteExpense(String expenseType, int index) throws ArrayIndexOutOfBoundsException,
            IllegalArgumentException{
        for (ExpenseCategory category : expenseCategories) {
            if (expenseType.toLowerCase().equals(category.getCategoryName().toLowerCase())) {   //getCategoryName()
                int categorySize = category.getExpenses().size();
                if (index > categorySize || index < 0) {
                    throw new ArrayIndexOutOfBoundsException("OOPS, please key in an index between 1 to "
                            + categorySize + "for this category: " + category.getCategoryName());
                }
                Expense deletedExpense = category.delete(index);
                return deletedExpense;
            }
        }
        throw new IllegalArgumentException("OOPS, this is not an expense category.");
    }

    private double totalExpenses() {
        double sumOfExpenses = 0.0;
        for(ExpenseCategory category : expenseCategories) {
            sumOfExpenses += category.getSumOfExpenses();
        }
        return sumOfExpenses;
    }

    public String printList() {
        StringBuilder listBuilder = new StringBuilder();
        listBuilder.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        listBuilder.append("Here is the list of all expenses by category: \n");
        listBuilder.append("~~~~~~~~~~~~~~~~~~~~\n");
        for (ExpenseCategory category : expenseCategories) {
            listBuilder.append(category.printExpenses());
            listBuilder.append("~~~~~~~~~~~~~~~~~~~~\n");
        }
        listBuilder.append("Total expenses: $ ");
        listBuilder.append(this.totalExpenses());
        listBuilder.append("\n");
        listBuilder.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        return listBuilder.toString();
    }

}