import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // Greet user
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        // Ask initial user input
        String userinput = scanner.nextLine();

        while (!userinput.equals("bye")) {
            // List
            if (userinput.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                // Output current items in list
                for (int i = 0; i < tasks.size(); i++) {
                    int currentItemNumber = i + 1;
                    Task currentTask = tasks.get(i);
                    System.out.println(currentItemNumber + ".[" + currentTask.getStatusIcon() + "] " + currentTask.getName());
                }
            }
            else {
                // Done
                String[] words = userinput.split(" ");
                if (words[0].equals("done")) {
                    int itemId = Integer.parseInt(words[1]);
                    Task currentTask = tasks.get(itemId - 1);
                    currentTask.setDone(true);
                    System.out.println("Nice! I've marked this task as done:\n[✓] " + currentTask.getName());
                }
                // Add
                else {
                    // Add user input to list and output
                    tasks.add(new Task(userinput, false));
                    System.out.println("added: " + userinput);
                }
            }
            // Ask for next userinput
            userinput = scanner.nextLine();
        }

        // At this point userinput equals "bye"
        System.out.println("Bye. Hope to see you again soon!");
    }
}
