import java.util.ArrayList;

public class Task {
    private String description;
    private boolean status = false;
    private static ArrayList<Task> taskList = new ArrayList<>();

    public Task(String description) {
        this.description = description;
        taskList.add(this);
        System.out.println("added: " + this);
    }

    private String getStatusIcon() {
        if (this.status) {
            return "[\u2713] ";
        } else {
            return "[\u2718] ";
        }
    }

    private void markAsDone() {
        this.status = true;
    }

    @Override
    public String toString() {
        return this.description;
    }

    public static void doTask(int index) {
        if (index > taskList.size()) {
            return;
        }
        Task task = taskList.get(index - 1);
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.getStatusIcon() + task);
    }

    public static void printList() {
        System.out.println("Here are the tasks in your list:");
        int counter = 1;
        for (Task task : taskList) {
            System.out.println(counter++ + "." + task.getStatusIcon() + task);
        }
    }
}
