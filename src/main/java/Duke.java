import java.util.Scanner;

public class Duke {
    private static String greetingMsg = "    ____________________________________________________________\n" +
            "     Hello! I'm Duke\n" +
            "     What can I do for you?\n" +
            "    ____________________________________________________________";
    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println(greetingMsg);
        boolean exit = false;
        while (!exit) {
            String input = sc.next();
            if (input.equals("bye")) {
                exit = true;
            } else
                System.out.println(input + "\n");
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void main(String[] args) {
        run();
    }
}

