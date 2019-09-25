import java.util.ArrayList;

public class ExpenseList extends CompleteList {

    protected static ArrayList<Expenses> listOfExpenses = new ArrayList<Expenses>();

    public ExpenseList() {

    }

    public ExpenseList(ArrayList<Expenses> list) {
        listOfExpenses = list;
    }

    public void addToExpensesList(Expenses assignment) {
        assert assignment != null;
        listOfExpenses.add(assignment);
    }
}
