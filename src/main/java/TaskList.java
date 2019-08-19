import java.util.ArrayList;

public class TaskList {
    public static int numTasks = 0;
    private ArrayList<Task> allTasks;

    public TaskList() {
        allTasks = new ArrayList<>();
    }

    public void addTask(Task newTask) {
        numTasks++;
        allTasks.add(newTask);
        //System.out.println(tasks.get(numTasks-1));
    }

    public void printAllTasks() {
        for (int i = 1; i <= numTasks; i++) {
            System.out.println(i + "." + allTasks.get(i-1));
        }
    }

    public void markAsDone(int index) {
        Task completedTask = allTasks.get(index - 1);
        if (!completedTask.isDone()) {
            completedTask.markAsDone();
        }
    }

}