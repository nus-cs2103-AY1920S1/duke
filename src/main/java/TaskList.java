import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> arr;

    public TaskList(ArrayList<Task> arrayList) {
        arr = arrayList;
    }

    public TaskList() {
        arr = new ArrayList<Task>();
    }

    public ArrayList<Task> getArr() {
        return arr;
    }

    public int size() {
        return arr.size();
    }

    public Task get(int index) {
        return arr.get(index);
    }

    public Task remove(int index) {
        return arr.remove(index);
    }

    public void add(Task task) {
        arr.add(task);
    }
}
