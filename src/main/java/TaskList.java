import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void removeTask(int index) {
        taskList.remove(index);
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public int getSize() {
        return taskList.size();
    }
}