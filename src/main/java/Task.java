import java.util.ArrayList;

public class Task {
    private String description;
    private boolean status = false;
    private static ArrayList<Task> taskList = new ArrayList<>();

    public Task(String description) {
        this.description = description;
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
        return this.getStatusIcon() + this.description;
    }

    public static void doTask(int index) {
        Task task = taskList.get(index - 1);
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public static void deleteTask(int index) {
        Task task = taskList.remove(index - 1);
        System.out.println("Noted! I've removed this task:");
        System.out.println(task);
        if (taskList.size() == 1)  {
            System.out.println("Now you have 1 task in the list!");
        } else {
            System.out.println("Now you have " + taskList.size() + " tasks in the list!");
        }
    }

    public static void printList() {
        System.out.println("Here are the tasks in your list:");
        int counter = 1;
        for (Task task : taskList) {
            System.out.println(counter++ + "." + task);
        }
    }

    protected static void addNewTask(Task task) {
        taskList.add(task);
        System.out.println("Got it! I've added this task:");
        System.out.println(task);
        if (taskList.size() == 1)  {
            System.out.println("Now you have 1 task in the list!");
        } else {
            System.out.println("Now you have " + taskList.size() + " tasks in the list!");
        }
    }
}
