import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    //@@author Parcly-Taxel
    /**
      * Pretty-prints a line of prompt to the user.
      */
    public static void printPrompt(String prompt) {
        System.out.println("    " + prompt);
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<Task>();
        
        printPrompt("Hello! I'm Duke");
        printPrompt("What can I do for you?");
        
        while (true) {
            // Read a whole line at once, then parse it
            String cmd = sc.nextLine();

            if (cmd.equals("bye")) {
                break;
            } else if (cmd.equals("list")) {
                printPrompt("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    printPrompt((i + 1) + "." + tasks.get(i));
                }
            } else if (cmd.startsWith("done")) {
                int i = Integer.parseInt(cmd.substring(5)) - 1;
                Task currTask = tasks.get(i);
                currTask.markDone();
                printPrompt("Nice! I've marked this task as done:");
                printPrompt("  " + currTask);
            } else {
                Task t = null;
                if (cmd.startsWith("deadline")) {
                    t = Deadline.parse(cmd);
                } else if (cmd.startsWith("event")) {
                    t = Event.parse(cmd);
                } else if (cmd.startsWith("todo")) {
                    t = Todo.parse(cmd);
                }
                tasks.add(t);
                int numTasks = tasks.size();
                printPrompt("Got it. I've added this task:");
                printPrompt("  " + t);
                printPrompt("Now you have " + numTasks + " task" + 
                            (numTasks == 1 ? "" : "s") + " in the list.");
            }
        }
        
        printPrompt("Bye. Hope to see you again soon!");
    }
}
