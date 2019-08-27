import java.io.*;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class TaskManager implements Manager {
    private List<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList();
    }

    public void readFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("/Users/bj/java/Duke/data/duke.txt"));
            reader.lines().forEach(line->
                                   tasks.add(Task.decode(line, this.tasks)));
        } catch (IOException io) {
            System.out.print(io);
        }
    }

    public void writeFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/bj/java/Duke/data/duke.txt"));
            for (Task task : tasks) {
                writer.write(task.encode() + "\n");
            }
            writer.close();
        } catch (IOException io) {
            System.out.println(io);
        }
    }

    public void add(String type, String description, String additional) {
        switch (type) {
        case "todo":
            addTodo(description);
            break;
        case "deadline":
            addDeadline(description, additional);
            break;
        case "event":
            addEvent(description, additional);
            break;
        default:
            throw new InvalidArgumentException();
        }
        System.out.println("     ------------------------------------------------------------");
        System.out.println("     Got it. I've added this task:");
        System.out.println("     \u2795  " + tasks.get(tasks.size() - 1).toString());
        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("     ------------------------------------------------------------");
    }

    public void addTodo(String description) {
        Task task = new Todo(description);
        tasks.add(task);
    }

    public void addDeadline(String description, String by) {
        Task task = new Deadline(description, by);
        tasks.add(task);
    }

    public void addEvent(String description, String at) {
        Task task = new Event(description, at);
        tasks.add(task);
    }

    public void done(int arg) {
        int index = arg - 1;
        Task task = tasks.get(index);
        task.markAsDone();
        System.out.println("    ------------------------------------------------------------");
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("      " + task);
        System.out.println("    ------------------------------------------------------------");
    }

    public void delete(int arg) {
        int index  = arg - 1;
        Task task = tasks.get(index);
        tasks.remove(index);
        System.out.println("    ------------------------------------------------------------");
        System.out.println("    Noted. I've removed this task:");
        System.out.println("    \uD83D\uDDD1  " + task.toString());
        System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("    ------------------------------------------------------------");
    }

    public void list() {
        int i = 1;
        System.out.println("    ============================================================");
        System.out.println("    Here are the tasks in your list:");
        System.out.println("    ------------------------------------------------------------");
        for (Task task : tasks) {
            System.out.println("     " + i + "." + task);
            i++;
        }
        System.out.println("    ============================================================");
    }

}
