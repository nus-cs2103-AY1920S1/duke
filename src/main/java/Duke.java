import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task > list = new ArrayList<>();
        greetHello();

        String input;
        // Run input loop
        while (!(input = sc.nextLine()).equals("bye")) {

            // Check if task is to be done or to read or add list
            if (input.startsWith("done ")) {
                doneTask(input, list);
            } else {
                switch (input) {
                    case "list":
                        readList(list);
                        break;
                    default:
                        addToList(new Task(input), list);
                        break;
                }
            }
        }

        greetBye();
    }

    public static void printOutput(String output) {
        String line = "    ____________________________________________________________\n";

        // Indent and process output line
        output = "      " + output.replaceAll("\n","\n      ") + '\n';

        System.out.println(line + output + line);
    }


    public static void greetHello() {
        printOutput("Hello I'm Duke\nWhat can I do for you?");
    }

    public static void greetBye() {
        printOutput("Bye. Hope to see you again soon!");
    }

    public static void addToList(Task task, ArrayList<Task> list) {
        list.add(task);
        printOutput("added: " + task.getDescription());
    }

    public static void readList(ArrayList<Task> list) {
        int count = 1;
        StringBuilder output = new StringBuilder();

        output.append("Here are the tasks in your list:\n");

        // List and print all items stored
        for (Task item: list) {
            output.append(count++ + ".[" + item.getStatusIcon() + "] " + item.getDescription() + '\n');
        }

        // Remove terminal newline character if at least 1 item inserted
        if (count > 1) {
            output.deleteCharAt(output.toString().length() - 1);
            printOutput(output.toString());
        } else {
            printOutput("Error : Nothing has been added yet!");
        }
    }

    public static void doneTask(String input, ArrayList<Task> list) {
        // Process input
        if (input.length() > 5) {
            try {
                int taskIndex = Integer.parseInt(input.substring(5));
                Task item = list.get(taskIndex-1);
                item.setDone();
                printOutput("Nice! I've marked this task as done:\n  [" + item.getStatusIcon() + "] " + item.getDescription());
            } catch (NumberFormatException e) {
                printOutput("Error : Please enter a number!");
            }
        } else {
            printOutput("Error : Input too short!");
        }

    }
}
