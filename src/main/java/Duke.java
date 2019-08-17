import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String input;
        int index;
        Scanner sc = new Scanner(System.in);
        List<String> list = new ArrayList<>();

        System.out.println("    " + "____________________________________________________________");
        System.out.println("    " + "Hello! I'm Duke");
        System.out.println("    " + "What can I do for you?");
        System.out.println("    " + "____________________________________________________________\n");

        while (true) {
            input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("    " + "____________________________________________________________");
                System.out.println("    " + "Bye. Hope to see you again soon!");
                System.out.println("    " + "____________________________________________________________\n");
                break;
            }
            else if (input.equals("list")) {
                System.out.println("    " + "____________________________________________________________");
                index = 1;
                for (String s : list) System.out.println("    " + (index++) + ". " + s);
                System.out.println("    " + "____________________________________________________________\n");
            }
            else {
                list.add(input);
                System.out.println("    " + "____________________________________________________________");
                System.out.println("    " + "Added: " + input);
                System.out.println("    " + "____________________________________________________________\n");
            }
        }
    }
}
