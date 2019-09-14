package seedu.duke;

import java.util.ArrayList;

public class StorageStub extends Storage {
    public StorageStub(String fp) {
        super(fp);
    }

    @Override
    public ArrayList<Task> loadTaskFile() {
        ArrayList<Task> arr = new ArrayList<>();
        arr.add(new Todo("abc"));
        arr.add(new Todo("def"));
        return arr;
    }

    @Override
    public ArrayList<Expense> loadExpenseFile() {
        ArrayList<Expense> arr = new ArrayList<>();
        arr.add(new Expense("ghi", 120));
        arr.add(new Expense("jkl", 130));
        return arr;
    }

    @Override
    public double loadIncomeFile() {
        return 0.0;
    }

}
