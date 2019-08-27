import java.util.ArrayList;

public class Task {
    private String description;
    private boolean isDone = false;
    private static Database db = new Database("./data/duke.txt");
    private static ArrayList<Task> taskList = db.load();

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

    protected void markAsDone() {
        this.isDone = true;
    }

    public String toDataString() {
        return (this.isDone ? 1 : 0) + " | " + this.description;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + this.description;
    }

    private static void updateDatabase() throws DukeException {
        db.store(taskList);
    }

    public static void addNewTask(Task task) throws DukeException {
        taskList.add(task);
        Console.print("Got it! I've added this task:");
        Console.print(task.toString());
        if (taskList.size() == 1)  {
            Console.print("Now you have 1 task in the list!");
        } else {
            Console.print("Now you have " + taskList.size() + " tasks in the list!");
        }
        updateDatabase();
    }

    public static void doTask(int index) throws DukeException {
        Task task = taskList.get(index - 1);
        task.markAsDone();
        Console.print("Nice! I've marked this task as done:");
        Console.print(task.toString());
        updateDatabase();
    }

    public static void deleteTask(int index) throws DukeException {
        Task task = taskList.remove(index - 1);
        Console.print("Noted! I've removed this task:");
        Console.print(task.toString());
        if (taskList.size() == 1)  {
            Console.print("Now you have 1 task in the list!");
        } else {
            Console.print("Now you have " + taskList.size() + " tasks in the list!");
        }
        updateDatabase();
    }

    public static void printList() {
        Console.print("Here are the tasks in your list:");
        int counter = 1;
        for (Task task : taskList) {
            Console.print(counter++ + "." + task);
        }
    }
}
