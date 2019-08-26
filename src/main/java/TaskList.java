import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskArr;

    public TaskList() {
        this.taskArr = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> taskArr) {
        this.taskArr = new ArrayList<Task>(taskArr);
    }

    public ArrayList<Task> getTaskArr() {
        return this.taskArr;
    }

    public int getTaskCount() {
        return this.getTaskArr().size();
    }

    public void markTaskDone(int index) {
        this.getTaskArr().get(index).markAsDone();
    }

    public Task getTask(int index) {
        return this.getTaskArr().get(index);
    }

    public void addTask(Task task) {
        this.getTaskArr().add(task);
    }

    public void deleteTask(int index) {
        this.getTaskArr().remove(index);
    }

}
