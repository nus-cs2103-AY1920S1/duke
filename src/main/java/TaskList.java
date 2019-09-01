import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public void addTask(Task task) {
        list.add(task);
        System.out.println("Got it. I've added this task:\n" + task
                + "\nNow you have " + list.size() + " tasks in the list.");
    }

    public void deleteTask(int deleteIndex) {
        Task temp = list.remove(deleteIndex - 1);
        System.out.println("Noted. I've removed this task:\n" + temp
                + "\nNow you have " + list.size() + " tasks in the list.");
    }

    public Task getTask(int taskIndex) {
        return list.get(taskIndex - 1);
    }

    public void showTaskList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + "." + list.get(i));
        }
    }

    public void setDoneTask(int doneIndex) {
        list.get(doneIndex - 1).markAsDone();
        System.out.println("Nice! I've marked this task as done:\n"
                + list.get(doneIndex - 1));
    }

    public ArrayList<Task> getTaskList() {
        return list;
    }
}
