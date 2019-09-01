import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> _tasks;

    public TaskList() {
        this._tasks = new ArrayList<>();
    }

    public void addTask(Task t) {
        this._tasks.add(t);
    }

    public void removeTask(int index) {
        this._tasks.remove(index);
    }

    public Task getTask(int index) {
        return this._tasks.get(index);
    }

    public int getSize() {
        return this._tasks.size();
    }
}
