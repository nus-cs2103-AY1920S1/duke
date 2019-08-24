import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasksList;

    public TaskList(ArrayList<Task> tasksList) {
        this.tasksList = tasksList;
    }

    public void addItem(Task task) {
        tasksList.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + task);
        System.out.printf("Now you have %d tasks in the list.\n", tasksList.size());
    }

    public void markIndexedTaskAsDone(int index) {
        getTaskAtIndex(index - 1).markAsDone();
    }

    public ArrayList<Task> getTasksList() {
        return tasksList;
    }

    public void deleteTask(int position) {
        Task task = tasksList.remove(position - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println("\t" + task);
        System.out.printf("Now you have %d tasks in the list.\n", tasksList.size());
    }

    public Task getTaskAtIndex(int position) {
        return tasksList.get(position);
    }

}
