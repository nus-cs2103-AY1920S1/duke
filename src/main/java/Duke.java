import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
     
        Scanner sc = new Scanner(System.in);

        // Print initial welcome string 
        System.out.println("    --------------------------------------------------------");
        System.out.println(" Hello! I'm Duke :)\n What can I do for you?");
        System.out.println("    --------------------------------------------------------");

        // initialize a TaskList
        TaskList tl = new TaskList();

        // Run till user types bye
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                tl.listTasks();
            } else {
                tl.addTask(input);
            }
            input = sc.nextLine();
        }

        // Print exit string
        System.out.println("    --------------------------------------------------------");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    --------------------------------------------------------");
    }
}

class TaskList {
    // ArrayList to maintain list of tasks
    ArrayList<String> tasks;

    public TaskList() {
        this.tasks = new ArrayList<String>();
    }

    // add tasks
    public void addTask(String task) {
        tasks.add(task);
        System.out.println("    --------------------------------------------------");
        System.out.println("     " + "added: " + task);
        System.out.println("    --------------------------------------------------");
    }

    // list tasks
    public void listTasks() {
        System.out.println("    --------------------------------------------------");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + Integer.toString(i+1) + ". " + tasks.get(i));
        }
        System.out.println("    --------------------------------------------------");
    }
}
