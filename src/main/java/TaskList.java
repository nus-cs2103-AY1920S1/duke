import java.util.ArrayList;

/**
 * Represents a list of tasks that are currently available.
 * It is able to add or delete tasks from the current list.
 */
public class TaskList {

    private ArrayList<Task> listOfTasks;

    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    public void addTask(Task task) {
        listOfTasks.add(task);
    }

    public void deleteTask(int taskNumber) {
        listOfTasks.remove(taskNumber);
    }

    public ArrayList<Task> getListOfTasks() {
        return listOfTasks;
    }
}