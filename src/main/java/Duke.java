import java.util.LinkedList;
import java.util.Scanner;

public class Duke {
    private static  LinkedList<Task> tasks = new LinkedList<Task>();
    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

         */
        System.out.println("Hello I'm Duke\n" + "What can I do for you?\n");
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String curr = sc.nextLine();
            if (curr.equals("bye")) {
               System.out.println("Bye. Hope to see you again soon!");
               break;
            } else {
                execute(curr);
            }
        }

    }

    private static void execute(String command) {
        if (command.equals("list")) {
            int i = 1;
            System.out.println("Here are the tasks in your list:");
            for (Task t : tasks) {
                System.out.println(i + ". " + t);
                i++;
            }
        } else if (command.startsWith("done ")) {
            int id = Integer.parseInt(command.substring(5));
            Task finished_task = tasks.get(id - 1);
            finished_task.markAsDone();
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println(finished_task);
        } else {
            addTask(command);
        }
    }

    public static void addTask(String command) {
        System.out.println("Got it. I've added this task: ");
        Task curr;
        if (command.startsWith("todo ")) {
            curr = new Todo(command);
            tasks.add(curr);

        } else if (command.startsWith("deadline ")) {
            curr = new Deadline(command);
            tasks.add(curr);
        } else if (command.startsWith("event ")) {
            curr = new Event(command);
            tasks.add(curr);
        } else {
            curr = new Task(command);
            tasks.add(curr);
        }
        System.out.println(curr);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");

    }


}
