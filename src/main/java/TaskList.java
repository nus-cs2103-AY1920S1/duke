import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> list;
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }
    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public ArrayList<Task> getList() {
        return list;
    }

    public void addTask(Task taskToAdd) {
        list.add(taskToAdd);
    }

    public Task deleteTask(int taskNumToDelete) {
        return list.remove(taskNumToDelete-1);
    }

    public void doneTask(int taskNumToMark) {
        Task t = list.get(taskNumToMark - 1);
        t.markAsDone();
    }
}
