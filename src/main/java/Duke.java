import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private Scanner sc = new Scanner(System.in);
    private String LINES = "\n" + "____________________________________________________________" + "\n";
    private ArrayList list = new ArrayList<Task>();

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }

    private void start() {
        System.out.println(display("Hello! I'm Duke\nWhat can I do for you?"));
        run();
    }

    private void run() {
        String s = sc.nextLine();

        while (!s.equals("bye")) {
            String[] strArr = s.split(" ");

            if (s.equals("list")) {
                list();

            } else if (strArr[0].equals("done")) {
                done(strArr);

            } else {
                Task task = new Task("");

                if (strArr[0].equals("todo")) {
                    task = new ToDo(s.substring(5));

                } else if (strArr[0].equals("deadline")) {
                    String[] temp = s.split("/by");
                    task = new Deadline(temp[0].substring(9).trim(), temp[1].trim());

                } else if (strArr[0].equals("event")) {
                    String[] temp = s.split("/at");
                    task = new Event(temp[0].substring(6).trim(), temp[1].trim());
                } else {
                    // Throw some exception here
                }
                list.add(task);
                System.out.println(display("Got it. I've added this task:\n  " + task
                        + "\nNow you have " + list.size() + " tasks in the list."));
            }
            s = sc.nextLine();
        }
        exit();
    }

    private void list() {
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

    private void exit() {
        System.out.println(display("Bye. Hope to see you again soon!"));
    }

    private String display(String text) {
        return LINES + text + LINES;
    }
}
