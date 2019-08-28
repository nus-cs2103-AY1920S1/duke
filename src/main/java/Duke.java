import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        run();
    }

    private static void run() {
        System.out.println("\n" +
                "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n");
        TaskTracker tracker = new TaskTracker();
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        while (!command.equals("bye")) {
            tracker.process(command);
            command = sc.nextLine();
        }
        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________");
    }
}
