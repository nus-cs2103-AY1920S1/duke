import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String input;
        Scanner sc = new Scanner(System.in);

        System.out.println("    " + "____________________________________________________________");
        System.out.println("    " + "Hello! I'm Duke");
        System.out.println("    " + "What can I do for you?");
        System.out.println("    " + "____________________________________________________________\n");

        while (true) {
            input = sc.next();
            if (input.equals("bye")) {
                System.out.println("    " + "____________________________________________________________");
                System.out.println("    " + "Bye. Hope to see you again soon!");
                System.out.println("    " + "____________________________________________________________\n");
                break;
            }
            else if (input.equals("list")) {
                System.out.println("    " + "____________________________________________________________");
                System.out.println("    " + "list");
                System.out.println("    " + "____________________________________________________________\n");
            }
            else if (input.equals("blah")) {
                System.out.println("    " + "____________________________________________________________");
                System.out.println("    " + "blah");
                System.out.println("    " + "____________________________________________________________\n");
            }
        }
    }
}
