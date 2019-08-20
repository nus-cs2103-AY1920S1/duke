import org.jetbrains.annotations.NotNull;

import java.util.Scanner;
import java.util.LinkedList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedList<Task> allTasks = new LinkedList<>();
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
            System.out.println(line);
            if (t.description.equals("list")) {
                // Print all existing items in the list
                System.out.println("Here are the tasks in your list:");
                allTasks.forEach(x -> System.out.println((allTasks.indexOf(x) + 1) + ". " + x));
            } else if (t.description.contains("done")) {
                // Mark item of interest as done
                int indexToMark = Integer.parseInt(t.description.split(" ")[1]);
                allTasks.get(indexToMark - 1).markAsDone();
            } else if (t.description.contains("todo")) {
                //Process to-do-task
                String description = t.description.split("todo")[1];
                System.out.println(addTask(new ToDo(description), allTasks));
            } else if (t.description.contains("deadline")) {
                //Process deadline-task
                String description = t.description.split("deadline")[1];
                String[] details = description.split("/by");
                System.out.println(addTask(new Deadline(details[0], details[1]), allTasks));
            } else if (t.description.contains("event")) {
                //Process event-task
                String description = t.description.split("event")[1];
                String[] details = description.split("/at");
                System.out.println(addTask(new Event(details[0], details[1]), allTasks));
            } else {
                // Add item to list
                allTasks.add(t);
                System.out.println("added: " + t.description);
            }
            // Print a horizontal line and require next input
            System.out.println(line);
            t = new Task(sc.nextLine());
        }
        System.out.println(line + "\n Bye. Hope to see you again soon! \n" + line);
    }

    public static String addTask(Task t, @NotNull LinkedList<Task> allTasks) {
        allTasks.add(t);
        StringBuilder sb = new StringBuilder("Got it. I've added this task:\n" + t);
        if (allTasks.size() == 1) {
            sb.append("\nNow you have " + allTasks.size() + " task in the list.");
        } else {
            sb.append("\nNow you have " + allTasks.size() + " tasks in the list.");
        }
        return sb.toString();
    }
}
