import java.util.Scanner;
import java.util.ArrayList;

public class Duke extends Task {

    public Duke(String description) {
        super(description);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        ArrayList<Task> alist = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
        while (scanner.hasNext()) {
            String input = scanner.next();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:\n");
                int counter = 0;
                for(Task t: alist) {
                    counter++;
                    System.out.println(counter + ". " + t);
                }
            } else if (input.equals("done")) {
                String number = scanner.next();
                int index = Integer.parseInt(number);
                Task task = alist.get(index - 1);
                task.markDone();
                System.out.println("Nice! I've marked this task as done: \n" +
                        "[" + task.getStatusIcon() + "] " + task.description);
            } else if (input.equals("todo")) {
                String what = scanner.nextLine();
                Todo todo = new Todo(what);
                alist.add(todo);
                System.out.println("Got it. I've added this task:");
                System.out.println(todo);
                System.out.println("Now you have " + alist.size() + " tasks in the list.");
            } else if (input.equals("deadline")) {
                String when = scanner.nextLine();
                String[] parts = when.split("/");
                String desc = parts[0];
                String time = parts[1];
                Deadline deadline = new Deadline(desc);
                deadline.setTime(time);
                alist.add(deadline);
                System.out.println("Got it. I've added this task:");
                System.out.println(deadline);
                System.out.println("Now you have " + alist.size() + " tasks in the list.");
            } else if (input.equals("event")) {
                String where = scanner.nextLine();
                String[] parts = where.split("/");
                String desc = parts[0];
                String time = parts[1];
                Event event = new Event(desc);
                event.setTime(time);
                alist.add(event);
                System.out.println("Got it. I've added this task:");
                System.out.println(event);
                System.out.println("Now you have " + alist.size() + " tasks in the list.");
            } else {    //throw error
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}
