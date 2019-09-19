package duke.extension.expense;

import java.util.ArrayList;

public class ExpenseList {
    private static final ArrayList<ExpenseCategory> EXPENSECATEGORIES = new ArrayList<>();
    static {
        EXPENSECATEGORIES.add(new ExpenseCategory("food", new ArrayList<Expense>()));
        EXPENSECATEGORIES.add(new ExpenseCategory("transport", new ArrayList<Expense>()));
        EXPENSECATEGORIES.add(new ExpenseCategory("education", new ArrayList<Expense>()));
        EXPENSECATEGORIES.add(new ExpenseCategory("household", new ArrayList<Expense>()));
        EXPENSECATEGORIES.add(new ExpenseCategory("apparel", new ArrayList<Expense>()));
        EXPENSECATEGORIES.add(new ExpenseCategory("beauty", new ArrayList<Expense>()));
        EXPENSECATEGORIES.add(new ExpenseCategory("health", new ArrayList<Expense>()));
        EXPENSECATEGORIES.add(new ExpenseCategory("social", new ArrayList<Expense>()));
        EXPENSECATEGORIES.add(new ExpenseCategory("others", new ArrayList<Expense>()));
    }

    public ExpenseList() {
    }

    public ArrayList<ExpenseCategory> getExpenseCategories() {
        return EXPENSECATEGORIES;
    }

    public void addExpense(String expenseType, Expense newExpense) {
        for(ExpenseCategory category : EXPENSECATEGORIES) {
            if(expenseType.toLowerCase().equals(category)) {
                category.add(newExpense);
                break;
            }
            if(category.equals("others")) {
                throw new IllegalArgumentException("OOPS, this is not an expense category.");
            }
        }
    }

    public void deleteExpense(String expenseType, int index) {
        for(ExpenseCategory category : EXPENSECATEGORIES) {
            if(expenseType.toLowerCase().equals(category)) {
                category.delete(index);
                break;
            }
            if(category.equals("others")) {
                throw new IllegalArgumentException("OOPS, this is not an expense category.");
            }
        }
    }


}