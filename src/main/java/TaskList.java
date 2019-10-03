import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList = new ArrayList<>();

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void deleteTask(int taskNum) {
        this.taskList.remove(taskNum);
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

}