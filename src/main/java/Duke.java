import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String LINE = "    ____________________________________________________________\n";
    private static ArrayList<String> items;

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        // initialise items list
        items = new ArrayList<>();

        greet();
        getUserInput();
    }

    private static void greet() {
        String greeting = LINE
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + LINE;

        System.out.println(greeting);
    }

    private static void getUserInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        if (userInput.equals("bye")) {
            String farewellMessage = LINE
                    + "     Bye. Hope to see you again soon!\n"
                    + LINE;
            System.out.print(farewellMessage);
        } else if (userInput.equals("list")) {
            System.out.print(LINE);
            for (int i = 0; i < items.size(); i++) {
                System.out.println("     " + Integer.toString(i + 1) + ". " + items.get(i));
            }
            System.out.print(LINE);
            getUserInput();
        } else {
            items.add(userInput);
            System.out.print(LINE + "     added: " + userInput + "\n" + LINE);
            getUserInput();
        }
    }
}
