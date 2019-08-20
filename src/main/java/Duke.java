import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
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
