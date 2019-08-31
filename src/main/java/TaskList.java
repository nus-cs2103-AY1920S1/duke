import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    public ArrayList<Task> getList() {
        return this.taskList;
    }

    public void remove(int taskIndex) {
        taskList.remove(taskIndex);
    }

    public int size() {
        return taskList.size();
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public void complete(int index) {
        taskList.get(index).complete();
    }

    public void add(Task t) {
        taskList.add(t);
    }
}
