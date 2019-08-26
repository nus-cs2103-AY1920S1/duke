package duke.dukeTask;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskStore;

    public TaskList() {
        this.taskStore = new ArrayList<>(100);
    }

    public TaskList(ArrayList<Task> taskStore) {
        this.taskStore = taskStore;
    }

    public ArrayList<Task> toArrayList() {
        return taskStore;
    }

    public void addToList(Task addTask) {
        taskStore.add(addTask);
    }

    public void deleteFromList(int index) {
        taskStore.remove(index);
    }

    public Task getElement(int index) {
        return taskStore.get(index);
    }

    public int getSize() {
        return taskStore.size();
    }
}
