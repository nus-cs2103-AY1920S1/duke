import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<Task>();
    
    //@@author Parcly-Taxel
    /**
      * Prints a line of prompt to the user.
      */
    public static void printPrompt(String prompt) {
        System.out.println("    " + prompt);
    }
    
    /**
     * Prints a message saying that a task has been successfully added.
     */
    public static void printAddedTask(Task t) {
        printPrompt("Got it. I've added this task:");
        printPrompt("  " + t);
        int numTasks = tasks.size();
        printPrompt("Now you have " + numTasks + " task" +
                (numTasks == 1 ? "" : "s") + " in the list.");
    }
    
    /**
     * Prints a message congratulating the user on a task done.
     */
    public static void printDoneTask(Task t) {
        printPrompt("Nice! I've marked this task as done:");
        printPrompt("  " + t);
    }
    
    /**
     * Prints the current task list without changing it.
     */
    public static void printTaskList() {
        printPrompt("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            printPrompt((i + 1) + "." + tasks.get(i));
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        printPrompt("Hello! I'm Duke");
        printPrompt("What can I do for you?");
        
        while (true) {
            // Read the command and data separately, together comprising a line
            String cmd = sc.next();
            String data = sc.nextLine().trim();
            
            switch (cmd) {
                case "bye":
                    printPrompt("Bye. Hope to see you again soon!");
                    System.exit(0);
                case "list":
                    printTaskList();
                    continue;
                case "done":
                    int i = Integer.parseInt(data) - 1;
                    Task currTask = tasks.get(i);
                    currTask.markDone();
                    printDoneTask(currTask);
                    continue;
                default:
                    break;
            }
            
            try {
                Task t = null;
                switch (cmd) {
                    case "todo":
                        t = Todo.parse(data);
                        break;
                    case "event":
                        t = Event.parse(data);
                        break;
                    case "deadline":
                        t = Deadline.parse(data);
                        break;
                    default:
                        throw new IllegalArgumentException("\u2639 OOPS!!! " +
                                "I'm sorry, but I don't know what that means :-(");
                }
                tasks.add(t);
                printAddedTask(t);
            } catch (IllegalArgumentException e) {
                printPrompt(e.getMessage());
            }
        }
    }
}
