import java.util.AbstractCollection;
import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public static void listTask() {
        if (taskList.size() == 0) {
            System.out.println("List is Empty");
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(i + 1 + "." + taskList.get(i));
            }
        }
    }

    public static Task getTask(int index) {
        return taskList.get(index);
    }

    public static ArrayList<Task> getList() {
        return taskList;
    }

    public void addTask(Task newTask) {
        taskList.add(newTask);
    }

    public int noOfTask() {
        return taskList.size();
    }

    public void removeTask(int index) {
        taskList.remove(index);
    }
}
