import java.util.ArrayList;

public class TaskList {

    public ArrayList<Task> list;

    public TaskList(ArrayList<Task> tasks) {
        this.list = tasks;
    }

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public void input(Task task) {
        this.list.add(task);
    }

    public void delete(int index) {
        this.list.remove(index);
    }

    public int count() {
        return this.list.size();
    }

    public Task fetch(int index) {
        return this.list.get(index);
    }

    //crud operations
}