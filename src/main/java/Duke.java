import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<String>();
        String welcomeMessage = "    ____________________________________________________________\n"
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + "    ____________________________________________________________\n";
        System.out.println(welcomeMessage);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (!input.equals("list")) {
                list.add(input);
                System.out.println("    ____________________________________________________________\n"
                        + "     added: " + input + "\n"
                        + "    ____________________________________________________________\n");
            } else {
                System.out.println("    ____________________________________________________________");
                for (int i = 1; i <= list.size(); i++) {
                    System.out.println("     " + i + ". " + list.get(i-1));
                }
                System.out.println("    ____________________________________________________________\n");
            }
            input = sc.nextLine();
        }
        System.out.println("    ____________________________________________________________\n"
                + "     Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________\n");
    }
}
