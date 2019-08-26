package bin;

import java.util.ArrayList;

public class DataStorage {
    private ArrayList<Task> textStorage = new ArrayList<>();

    public String store(String task) {
        textStorage.add(new Task(task));
        return Constants.INDENTATION + "ADDED: " + task;
    }

    public Task markAsDone(int index) {
        return textStorage.get(--index).completed();
    }

    public ArrayList<Task> getList() {
        return textStorage;
    }
}
