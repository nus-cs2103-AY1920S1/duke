import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
     
        Scanner sc = new Scanner(System.in);

        // Print initial welcome string 
        System.out.println("    --------------------------------------------------------");
        System.out.println("     Hello! I'm Duke :)\n What can I do for you?");
        System.out.println("    --------------------------------------------------------");

        // initialize a TaskList
        TaskList tl = new TaskList();

        // Run till user types bye
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                tl.listTasks();
            } else if (input.split(" ")[0].equals("done")) {
                int index = Integer.parseInt(input.split(" ")[1]);
                tl.taskDone(index);
            } else {
                tl.addTask(new Task(input));
            }
            input = sc.nextLine();
        }

        // Print exit string
        System.out.println("    --------------------------------------------------------");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    --------------------------------------------------------");
    }
}

/** Class to represent a task. */
class Task {
    private String name;
    private boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    // mark task as done
    public void markDone() {
        this.done = true;
    }

    // get task name
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        String done_str = this.done ? "✓" : "✗";
        return String.format("[%s] %s", done_str, this.name);
    }
}

/** Class to represent a list of tasks. */
class TaskList {
    // ArrayList to maintain list of tasks
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    // add tasks
    public void addTask(Task task) {
        tasks.add(task);
        prettyPrint("added: " + task.getName());
    }

    // list tasks
    public void listTasks() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(String.format("     %d. %s\n", i+1, this.tasks.get(i).toString()));
        }
        prettyPrint(sb.toString());
    }

    // mark task as done
    public void taskDone(int index) {
        this.tasks.get(index-1).markDone();
        StringBuilder output = new StringBuilder("Nice! I've marked this task as done:\n");
        output.append(String.format("     %s", this.tasks.get(index-1).toString()));
        prettyPrint(output.toString());
    }

    // pretty print a string
    void prettyPrint(String str) {
        System.out.println("    --------------------------------------------------");
        System.out.println("     " + str);
        System.out.println("    --------------------------------------------------");
    }
}
