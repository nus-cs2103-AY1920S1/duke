import java.util.ArrayList;
import java.util.Iterator;

public class TaskList {
    private ArrayList<String> tasks = new ArrayList<>();

    public void addToList(String taskDescription) {
        tasks.add(taskDescription);
        System.out.println("added: " + taskDescription);
    }

    public void printList() {
        int i = 1;
        for (Iterator<String> iterator = this.tasks.iterator(); iterator.hasNext(); ) {
            String s = iterator.next();
            System.out.println(i + ". " + s);
            i++;
        }
    }
}
