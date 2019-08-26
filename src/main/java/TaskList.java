import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public ArrayList<Task> getList() {
        return list;
    }

    public void add(Task t) {
        list.add(t);
    }

    public Task delete(int index) {
        Task t = list.remove(index);
        return t;
    }

    public int size() {
        return list.size();
    }

    public Task get(int index) {
        return list.get(index);
    }
}
