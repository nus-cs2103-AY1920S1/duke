import java.util.ArrayList;
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
        ArrayList<String> inputs = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String user_input = sc.nextLine();
            if (user_input.equals("bye")) {
                System.out.println("    ____________________________________________________________\n" +
                        "     Bye. Hope to see you again soon!\n" +
                        "    ____________________________________________________________");
                System.exit(0);
            } else if (user_input.equals("list")) {
                System.out.println("    ____________________________________________________________");
                for (int i = 0; i < inputs.size(); i++) {
                    int list_num= i + 1;
                    System.out.println("    " + list_num + ". " + inputs.get(i));
                }
                System.out.println("    ____________________________________________________________\n");
            } else {
                inputs.add(user_input);
                System.out.println("    ____________________________________________________________\n" +
                        "     added: " + user_input + '\n' +
                        "    ____________________________________________________________\n");
            }
        }
    }

    public static void main(String[] args) {
        printGreeting();
        printInput();
    }
}
