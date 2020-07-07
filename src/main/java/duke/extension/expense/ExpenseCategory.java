package duke.extension.expense;

import java.util.ArrayList;

public class ExpenseCategory {
    private String categoryName;
    private ArrayList<Expense> expenses;
    private double sumOfExpenses;

    public ExpenseCategory(String categoryName, ArrayList<Expense> expenses) {
        this.categoryName = categoryName;
        this.expenses = expenses;
        sumOfExpenses = 0.0;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public ArrayList<Expense> getExpenses() {
        return this.expenses;
    }

    public double getSumOfExpenses() {
        return this.sumOfExpenses;
    }

    public void add(Expense expense) {
        this.expenses.add(expense);
        sumOfExpenses += expense.getAmount();
    }

    public Expense delete(int index) {
        Expense expenseRemoved = this.expenses.get(index);
        sumOfExpenses -= expenseRemoved.getAmount();
        this.expenses.remove(index);
        return expenseRemoved;
    }


    public String printExpenses() {
        StringBuilder expensesBuilder = new StringBuilder();
        int expenseId = 1;
        expensesBuilder.append(categoryName);
        expensesBuilder.append(":\n");
        for (Expense expense : expenses) {
            expensesBuilder.append(expenseId);
            expensesBuilder.append(". ");
            expensesBuilder.append(expense);
            expensesBuilder.append("\n");
            expenseId++;
        }
        expensesBuilder.append("\n");
        expensesBuilder.append("Sum:    $ ");
        expensesBuilder.append(String.format("%.2f", sumOfExpenses));
        expensesBuilder.append("\n");
        return expensesBuilder.toString();
    }
}
