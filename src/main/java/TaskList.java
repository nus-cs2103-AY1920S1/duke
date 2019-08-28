import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    public Task getTask(int idx) {
        return tasks.get(idx);
    }

    public void removeTask(Task taskToBeRemoved) {
        tasks.remove(taskToBeRemoved);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public void addTask(Task t) {
        this.tasks.add(t);
    }

}
