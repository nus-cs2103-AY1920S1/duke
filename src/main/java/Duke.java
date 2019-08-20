import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        */
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        System.out.print("    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n");
        while (!input.equalsIgnoreCase("bye")) {
            System.out.print("    ____________________________________________________________\n" +
                    "    "+ input + "\n" +
                    "    ____________________________________________________________\n");
            input = sc.next();
        }

        System.out.print("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________\n");
    }
}
