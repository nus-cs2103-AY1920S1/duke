import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        //Level 2
        Scanner sc = new Scanner(System.in);
        ArrayList<String> events = new ArrayList<>();
        String input = sc.nextLine();
        System.out.print("    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n");
        while (!input.equalsIgnoreCase("bye")) {
            if (input.equals("list")) {
                String result = "";
                for (int i = 0; i < events.size(); i = i + 1) {
                    result = result + "    " + (i + 1) + ". " + events.get(i) + "\n";
                }
                result = result.equals("") ? "\n" : result;
                System.out.print("    ____________________________________________________________\n" +
                         result +
                        "    ____________________________________________________________\n");
                input = sc.nextLine();
                continue;
            } else {
                System.out.print("    ____________________________________________________________\n" +
                        "    added: "+ input + "\n" +
                        "    ____________________________________________________________\n");
                events.add(input);
                input = sc.nextLine();
            }
        }

        System.out.print("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________\n");
    }
}
