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
            try {
                int id = Integer.parseInt(command.substring(5));
                Task finished_task = tasks.get(id - 1);
                finished_task.markAsDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(finished_task);
            } catch (NumberFormatException e) {
                System.out.println("☹ OOPS!!! Invalid index provided!");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! No such task exist!");
            }
        } else {
            addTask(command);
        }
    }

    public static void addTask(String command) {
        boolean valid = true;
        Task curr = null;
        if (command.startsWith("todo ")) {
            curr = new Todo(command);
            tasks.add(curr);

        } else if (command.startsWith("deadline ")) {
            if (!command.contains("/by ")) {
                System.out.println("☹ OOPS!!! Please follow format e.g deadline return book /by Sunday");
                valid = false;
            } else {
                curr = new Deadline(command);
                tasks.add(curr);
            }
        } else if (command.startsWith("event ")) {
            if (!command.contains("/at ")) {
                System.out.println("☹ OOPS!!! Please follow format e.g event project meeting /at Mon 2-4pm");
                valid = false;
            } else {
                curr = new Event(command);
                tasks.add(curr);
            }

        } else {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            valid = false;
        }
        
        if (valid) {
            System.out.println("Got it. I've added this task: ");
            System.out.println(curr);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        }

    }


}
