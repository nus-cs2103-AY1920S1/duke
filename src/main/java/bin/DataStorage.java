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

    public Task markAsDone(int index) throws DukeException {
        --index;
        if (index < 0 | index >= textStorage.size())
            throw new DukeException("There's no Task attached to that number");
        return textStorage.get(index).completed();
    }

    public ArrayList<Task> getList() {
        return textStorage;
    }
}
