package duke.extension.expense;

import java.util.ArrayList;

public class ExpenseCategory {
    private String categoryName;
    private ArrayList<Expense> expenses;

    public ExpenseCategory(String categoryName, ArrayList<Expense> expenses) {
        this.categoryName = categoryName;
        this.expenses = expenses;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public ArrayList<Expense> getExpenses() {
        return this.expenses;
    }

    public void add(Expense expense) {
        this.expenses.add(expense);
    }

    public void delete(int index) {
        this.expenses.remove(index);
    }
}
