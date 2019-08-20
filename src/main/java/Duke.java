import java.util.Scanner;
import java.util.LinkedList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedList<Task> allItems = new LinkedList<>();
        String line = "____________________________________________________________";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(line + "\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n" + line);

        // Read in input
        Task t = new Task(sc.nextLine());
        while (!t.description.equals("bye")) {
            System.out.println(line + "\n");
            if (t.description.equals("list")) {
                // Print all existing items in the list
                System.out.println("Here are the tasks in your list:\n");
                allItems.forEach(x -> System.out.println((allItems.indexOf(x) + 1) + ". " + x));
            } else if (t.description.contains("done")) {
                // Mark item of interest as done
                int indexToMark = Integer.parseInt(t.description.split(" ")[1]);
                allItems.get(indexToMark - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done:\n");
                System.out.println(allItems.get(indexToMark - 1));
            } else {
                // Add item to list
                allItems.add(t);
                System.out.println("added: " + t.description);
            }
            // Print a horizontal line and require next input
            System.out.println("\n" + line);
            t = new Task(sc.nextLine());
        }
        System.out.println(line + "\n Bye. Hope to see you again soon! \n" + line);
    }
}
