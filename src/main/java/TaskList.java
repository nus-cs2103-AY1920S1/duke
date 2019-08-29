import java.util.ArrayList;
import java.util.Iterator;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public void addToList(String taskDescription) {
        tasks.add(new Task(taskDescription));
        System.out.println("added: " + taskDescription);
    }

    public void printList() {
        int i = 1;
        for (Iterator<Task> iterator = this.tasks.iterator(); iterator.hasNext(); ) {
            String s = iterator.next().printTask();
            System.out.println(i + ". " + s);
            i++;
        }
    }

    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex - 1);
    }
}
