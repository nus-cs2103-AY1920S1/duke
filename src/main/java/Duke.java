import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        while (sc.hasNext()) {
            String command = sc.nextLine();
            if (command.equals("list")) {
                System.out.println("list");
            } else if (command.equals("blah")) {
                System.out.println("blah");
            } else if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
        }

        sc.close();
    }
}
