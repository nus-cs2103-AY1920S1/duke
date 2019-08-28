import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> listOfTasks;

    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    public void addTask(Task task) {
        listOfTasks.add(task);
    }

    public void deleteTask(int taskNumber) {
        Task deletedTask = listOfTasks.get(taskNumber);
        listOfTasks.remove(taskNumber);
    }

    public ArrayList<Task> getListOfTasks() {
        return listOfTasks;
    }
}