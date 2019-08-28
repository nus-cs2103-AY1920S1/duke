import java.util.ArrayList;

/**
 * Manages users' task.
 */
public class TaskList {

    private ArrayList<Task> taskArr;

    /**
     * Construct a list to record the tasks.
     */
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

    public void markTaskDone(int index) throws DukeException {
        Task task = this.getTaskArr().get(index);
        if (!task.isDone()) {
            this.getTaskArr().get(index).markAsDone();
        } else {
            throw new DukeException("☹ The task is already marked done.");
        }
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
