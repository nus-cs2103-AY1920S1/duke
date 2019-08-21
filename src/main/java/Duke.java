import java.util.Scanner;

public class Duke {

    public static void printGreeting() {
        String greet_msg = "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n";
        System.out.println(greet_msg);
    }

    public static void printInput() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String user_input = sc.next();
            if (user_input.equals("bye")) {
                System.out.println("    ____________________________________________________________\n" +
                        "     Bye. Hope to see you again soon!\n" +
                        "    ____________________________________________________________");
                System.exit(0);
            } else {
                System.out.println("    ____________________________________________________________\n" +
                        "     " + user_input + '\n' +
                        "    ____________________________________________________________\n");
            }
        }
    }

    public static void main(String[] args) {
        printGreeting();
        printInput();
    }
}
