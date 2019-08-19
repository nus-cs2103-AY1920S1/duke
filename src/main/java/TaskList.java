import java.util.ArrayList;

public class TaskList {
    private ArrayList<String> allTasks;
    public static int numTasks = 0;

    public TaskList() {
        allTasks = new ArrayList<>();
    }

    public void addTask(String newTask) {
        numTasks++;
        allTasks.add(numTasks + ". " + newTask);
        //System.out.println(tasks.get(numTasks-1));
    }

    public void printAllTasks() {
        for (String task : allTasks) {
            System.out.println(task);
        }
    }

}