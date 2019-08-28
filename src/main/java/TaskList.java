import java.util.LinkedList;

public class TaskList {
    private LinkedList<Task> taskList;

    public TaskList() {
        taskList = new LinkedList<>();
    }

    public TaskList(LinkedList<Task> taskList) {
        this.taskList = taskList;
    }

    public void deleteTask(int taskNum) {
        taskList.remove(taskNum -1);
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public LinkedList<Task> getList() {
        return taskList;
    }

    public Task getTask(int taskNum) {
        return taskList.get(taskNum -1);
    }

    public int size() {
        return taskList.size();
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }
}