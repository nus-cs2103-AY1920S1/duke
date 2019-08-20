import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public Duke() {}

    private void run() throws DukeException {
        ArrayList<Task> alist = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            try {
                String input = scanner.next();
                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!\n");
                } else if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list:\n");
                    int counter = 0;
                    for (Task t : alist) {
                        counter++;
                        System.out.println(counter + ". " + t);
                    }
                } else if (input.equals("delete")) {
                    int index = scanner.nextInt();
                    if(index > alist.size()) {
                        throw new DukeException("OOPS!!! Task not found.");
                    }
                    Task task = alist.get(index - 1);
                    alist.remove(task);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(task);
                    System.out.println("Now you have " + alist.size() + " tasks in the list.");
                } else if (input.equals("done")) {
                    int index = scanner.nextInt();
                    if(index > alist.size()) {
                        throw new DukeException("OOPS!!! Task not found.");
                    }
                    Task task = alist.get(index - 1);
                    task.markDone();
                    System.out.println("Nice! I've marked this task as done: \n" +
                            "[" + task.getStatusIcon() + "] " + task.description);
                } else if (input.equals("todo")) {
                    String what = scanner.nextLine();
                    if (what.isEmpty()) {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    Todo todo = new Todo(what);
                    alist.add(todo);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(todo);
                    System.out.println("Now you have " + alist.size() + " tasks in the list.");
                } else if (input.equals("deadline")) {
                    String when = scanner.nextLine();
                    if (when.isEmpty()) {
                        throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                    }
                    String[] parts = when.split("/");
                    if (parts.length == 1) {
                        throw new DukeException("OOPS!!! The time of a deadline cannot be empty.");
                    }
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
                    if (where.isEmpty()) {
                        throw new DukeException("OOPS!!! The description of an event cannot be empty.");
                    }
                    String[] parts = where.split("/");
                    if (parts.length == 1) {
                        throw new DukeException("OOPS!!! The time of an event cannot be empty.");
                    }
                        String desc = parts[0];
                        String time = parts[1];
                        Event event = new Event(desc);
                        event.setTime(time);
                        alist.add(event);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(event);
                        System.out.println("Now you have " + alist.size() + " tasks in the list.");
                } else {    //invalid task name
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
        Duke newDuke = new Duke();
        newDuke.run();
    }
}
