import java.util.Scanner;

public class Duke {
    private static final String LINE = "    ____________________________________________________________\n";

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
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
            System.out.println(farewellMessage);
        } else {
            System.out.println(LINE + "     " + userInput + "\n" + LINE);
            getUserInput();
        }
    }
}
