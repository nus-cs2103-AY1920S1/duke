import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task getTask(int taskNumber) {
        return this.tasks.get(taskNumber);
    }

    public void deleteTask(int taskNumber) {
        this.tasks.remove(taskNumber);
    }

    public void markAsDone(int taskNumber) {
        this.tasks.get(taskNumber).markAsDone();
    }

    public int taskListSize() {
        return this.tasks.size();
    }

}
