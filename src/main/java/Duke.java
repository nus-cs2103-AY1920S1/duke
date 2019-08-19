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

        // Reads in input
        Task t = new Task(sc.nextLine());
        while (!t.description.equals("bye")) {
            System.out.println(line + "\n");
            if (t.description.equals("list")) {
                System.out.println("Here are the tasks in your list:\n");

                // Prints Each Item in the format "1. Item"
                allItems.forEach(x -> System.out.println((allItems.indexOf(x) + 1) + x.toString()));
            } else if (t.description.contains("done")) {
                t.markAsDone();
                System.out.println("Nice! I've marked this task as done:\n");
            } else {
                allItems.add(t);
                //Prints "added: Item"
                System.out.println("added: " + t.description);
            }
            System.out.println("\n" + line);
            t = new Task(sc.nextLine());
            System.out.println(allItems.size());
        }
        System.out.println(line + "\n Bye. Hope to see you again soon! \n" + line);
    }
}
