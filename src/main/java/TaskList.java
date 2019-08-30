import java.util.ArrayList;
import java.util.Iterator;

public class TaskList {

    private ArrayList<Task> tasks = new ArrayList<>();

    public enum TaskType {
        DEADLINE,
        EVENT,
        TODO
    }

    public void addToList(String input) {
        // determine type of task
        TaskType taskType;
        if (input.startsWith("todo")) {
            taskType = TaskType.TODO;
        } else if (input.startsWith("deadline")) {
            taskType = TaskType.DEADLINE;
        } else if (input.startsWith("event")) {
            taskType = TaskType.EVENT;
        }
        // todo: how to create corresponding subclass object based on enum?
        // todo: split description and other info
        tasks.add(new Task(taskDescription));
        System.out.println("added: " + taskDescription);
    }

    public void printList() {
        int i = 1;
        for (Iterator<Task> iterator = this.tasks.iterator(); iterator.hasNext(); i++) {
            String s = iterator.next().toString();
            System.out.println(i + ". " + s);
        }
    }

    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex - 1);
    }
}
