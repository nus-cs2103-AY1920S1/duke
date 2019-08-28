import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public int getListSize() {
        return list.size();
    }

    public Task getItemAtIndex(int i) {
        return list.get(i);
    }

    public Task getLastItem() { return list.get(list.size() - 1); }

    public ArrayList<Task> getList() {
        return list;
    }

    public void addItemToList(Task tsk) {
        list.add(tsk);
    }

    public void markAsDone(int i) {
        list.get(i).markAsDone();
    }

    public Task removeFromList(int i) {
        return list.remove(i);
    }
}
