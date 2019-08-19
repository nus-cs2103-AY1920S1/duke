import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private Scanner sc = new Scanner(System.in);
    private String LINES = "\n" + "____________________________________________________________" + "\n";
    private ArrayList list = new ArrayList<Task>();

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
        duke.run();
    }

    private void start() {
        System.out.println(display("Hello! I'm Duke\nWhat can I do for you?"));
    }

    private void run() {
        String s = sc.nextLine();

        while (!s.equals("bye")) {
            String[] strArr = s.split(" ");

            if (s.equals("list")) {
                printList();
            } else if (strArr[0].equals("done")) {
                done(strArr);
            } else {
                addTask(s, strArr);
            }
            s = sc.nextLine();
        }
        exit();
    }

    private void printList() {
        System.out.print(LINES);
        System.out.println("Here are the task in your list:");
        for (int i = 0; i < list.size() - 1; i++) {
            System.out.println(i + 1 + "." + list.get(i));
        }
        System.out.print(list.size() + "." + list.get(list.size() - 1));
        System.out.println(LINES);
    }

    private void done(String[] strArr) {
        ((Task) list.get(Integer.parseInt(strArr[1]) - 1)).markAsDone();
        System.out.println(display(
                "Nice! I've marked this task as done:\n"
                        + ((Task) list.get(Integer.parseInt(strArr[1]) - 1))

        ));
    }

    private void addTask(String s, String[] strArr) {
        Task task = new Task("");

        try {
            if (strArr[0].equals("todo")) {
                if (s.length() < 6) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                String description = s.substring(5);
                task = new ToDo(description);

            } else if (strArr[0].equals("deadline")) {
                if (s.length() < 10) {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
                String[] temp = s.split("/by");
                if (temp.length < 2) {
                    throw new DukeException("Please specify the deadline time using /by.");
                }
                String description = temp[0].substring(9).trim();
                String by = temp[1].trim();
                task = new Deadline(description, by);

            } else if (strArr[0].equals("event")) {
                if (s.length() < 7) {
                    throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                }
                String[] temp = s.split("/at");
                if (temp.length < 2) {
                    throw new DukeException("Please specify the event time using /at.");
                }
                String description = temp[0].substring(6).trim();
                String at = temp[1].trim();
                task = new Event(description, at);

            } else {
                // Not an action
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

            list.add(task);
            System.out.println(display("Got it. I've added this task:\n  " + task
                    + "\nNow you have " + list.size() + " tasks in the list."));
        } catch (DukeException e) {
            System.out.print(display(e.getMessage()));
        } catch (Exception e) {
            assert false : "Uncaught exception";
        }
    }

    private void exit() {
        System.out.println(display("Bye. Hope to see you again soon!"));
    }

    private String display(String text) {
        return LINES + text + LINES;
    }
}
