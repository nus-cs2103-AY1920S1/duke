import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LevelTwo {
    public static void run() {
        greet();
        List<String> list = new ArrayList<String>();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine(); // Initial Input
        while(!input.equals("bye")) {
            switch (input) {
                case "list":
                    echoList(list);
                    break;
                default:
                    list.add(input);
                    echoEntry(input);
            }
            input = sc.nextLine();
        }
        exit();
    }

    private static void greet() {
        System.out.println("    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________");
    }

    private static void echoEntry(String input) {
        System.out.println("    ____________________________________________________________\n" +
                String.format("     added: %s\n", input) +
                "    ____________________________________________________________");
    }

    private static void echoList(List<String> list) {
        System.out.println("    ____________________________________________________________");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(String.format("     %d. %s", i+1, list.get(i)));
        }
        System.out.println("    ____________________________________________________________");
    }

    private static void exit() {
        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________");
    }
}
