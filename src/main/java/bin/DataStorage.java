package bin;

import bin.task.Task;

import java.util.ArrayList;

public class DataStorage {
    private ArrayList<Task> textStorage = new ArrayList<>();

    public void store(Task task) {
        textStorage.add(task);
    }

    public int getSize() {
        return textStorage.size();
    }

    public Task markAsDone(int index) {
        return textStorage.get(--index).completed();
    }

    public ArrayList<Task> getList() {
        return textStorage;
    }
}
