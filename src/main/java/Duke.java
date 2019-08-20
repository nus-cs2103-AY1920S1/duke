import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________");

        String cmd = sc.nextLine();
        while (!cmd.equals("bye")) {
            System.out.println("    ____________________________________________________________");
            System.out.println("     " + cmd);
            System.out.println("    ____________________________________________________________");
            cmd = sc.nextLine();
        }
        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________");
    }
}
