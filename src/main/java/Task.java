import java.util.ArrayList;

public class Task {
    private String description;
    private boolean isDone = false;
    private static ArrayList<Task> taskList = new ArrayList<>();

    public Task(String description) {
        this.description = description;
    }

    private String getStatusIcon() {
        if (this.isDone) {
            return "[\u2713] ";
        } else {
            return "[\u2718] ";
        }
    }

    private void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + this.description;
    }

    public static void addNewTask(Task task) {
        taskList.add(task);
        Console.print("Got it! I've added this task:");
        Console.print(task.toString());
        if (taskList.size() == 1)  {
            Console.print("Now you have 1 task in the list!");
        } else {
            Console.print("Now you have " + taskList.size() + " tasks in the list!");
        }
    }

    public static void doTask(int index) {
        Task task = taskList.get(index - 1);
        task.markAsDone();
        Console.print("Nice! I've marked this task as done:");
        Console.print(task.toString());
    }

    public static void deleteTask(int index) {
        Task task = taskList.remove(index - 1);
        Console.print("Noted! I've removed this task:");
        Console.print(task.toString());
        if (taskList.size() == 1)  {
            Console.print("Now you have 1 task in the list!");
        } else {
            Console.print("Now you have " + taskList.size() + " tasks in the list!");
        }
    }

    public static void printList() {
        Console.print("Here are the tasks in your list:");
        int counter = 1;
        for (Task task : taskList) {
            Console.print(counter++ + "." + task);
        }
    }
}
