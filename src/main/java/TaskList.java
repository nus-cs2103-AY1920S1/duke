import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> xs;
    private int numOfTasks;

    public TaskList() {
        xs = new ArrayList<Task>();
        numOfTasks = 0;
    }

    public int getNumOfTasks() {
        return numOfTasks;
    }

    public ArrayList<Task> getTaskList() {
        return xs;
    }

    public void addPreviousTask(Task t) {
        numOfTasks++;
        xs.add(t);
    }

    public void addTask(Task t) {
        numOfTasks++;
        xs.add(t);
        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        System.out.printf("Now you have %d tasks in the list.\n", numOfTasks);
    }

    public void tickTask(int num) {
        xs.get(num - 1).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(xs.get(num - 1));
    }

    public void removeTask(int num) {
        numOfTasks--;
        Task t = xs.get(num - 1);
        xs.remove(num - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println(t);
        System.out.printf("Now you have %d tasks in the list.\n", numOfTasks);
    }

    public void printTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= xs.size(); i++) {
            System.out.println(i + "." + xs.get(i - 1));
        }
    }
}
