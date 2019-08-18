import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private Scanner sc = new Scanner(System.in);
    private String LINES = "\n" + "____________________________________________________________" + "\n";
    private ArrayList list = new ArrayList<Task>();

    public static void main (String[] args) {
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
            if (s.equals("list")) {
                // List task
                System.out.print(LINES);
                System.out.println("Here are the task in your list:");
                for (int i = 0; i < list.size() - 1; i++) {
                    System.out.println(i + 1 + ". " + list.get(i));
                }
                System.out.print(list.size() + ". " + list.get(list.size() - 1));
                System.out.println(LINES);
            } else {
                String[] strArr = s.split(" ");
                if (strArr[0].equals("done")) {
                    // Done task
                    ((Task) list.get(Integer.parseInt(strArr[1]) - 1)).markAsDone();
                    System.out.println(display(
                            "Nice! I've marked this task as done:\n"
                                    + ((Task) list.get(Integer.parseInt(strArr[1]) - 1))

                    ));
                } else {
                    // Add task
                    Task task = new Task(s);
                    System.out.println(display("added: " + s));
                    list.add(task);
                }
            }
            s = sc.nextLine();
        }
        exit();
    }

    private void exit() {
        System.out.println(display("Bye. Hope to see you again soon!"));
    }

    private String display(String text) {
        return LINES + text + LINES;
    }
}
