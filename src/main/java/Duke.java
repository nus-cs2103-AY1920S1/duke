import java.security.cert.CertificateRevokedException;
import java.util.Scanner;
import java.util.LinkedList;

public class Duke {
    public static void main(String[] args) throws DukeException {
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
            try {
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
                    try {
                        String description = t.description.split("todo")[1];
                        System.out.println(addTask(new ToDo(description), allTasks));
                    } catch (ArrayIndexOutOfBoundsException a) {
                        throw new DukeException("The description of a todo cannot be empty");
                    }
                } else if (t.description.contains("deadline")) {
                    //Process deadline-task
                    try {
                        String description = t.description.split("deadline")[1];
                        String[] details = description.split("/by");
                        System.out.println(addTask(new Deadline(details[0], details[1]), allTasks));
                    } catch (ArrayIndexOutOfBoundsException a) {
                        throw new DukeException("The description of a deadline cannot be empty.");
                    }
                } else if (t.description.contains("event")) {
                    //Process event-task
                    try {
                        String description = t.description.split("event")[1];
                        String[] details = description.split("/at");
                        System.out.println(addTask(new Event(details[0], details[1]), allTasks));
                    } catch (ArrayIndexOutOfBoundsException a) {
                        throw new DukeException("The description of an event cannot be empty");
                    }
                } else {
                    //Throw Exception
                    throw new DukeException("I'm sorry, but I don't know what that means :-( ");
                }
            } catch (DukeException e) {
                System.err.println(e);
            } finally {
                // Print a horizontal line and require next input
                System.out.println(line);
                t = new Task(sc.nextLine());
            }
        }
        System.out.println(line + "\n Bye. Hope to see you again soon! \n" + line);
    }

    public static String addTask(Task t, LinkedList<Task> allTasks) {
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
