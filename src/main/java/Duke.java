import java.util.Scanner;

public class Duke {

    public static final String LONG_LINE = "____________________________________________________________";
    public static final String GREETING = "Hello! I'm Duke\nWhat can I do for you?";
    public static final String BYE_STR = "Bye. Hope to see you again soon!";
    public static final String BYE_CMD = "bye";

    public static void main(String[] args) {
        printGreeting();

        Scanner input = new Scanner(System.in);
        String command = input.next();

        while (!command.equals(BYE_CMD)) {
            processCommand(command);
            command = input.next();
        }

        input.close();
        printGoodbye();
    }

    public static void processCommand(String command) {
        System.out.println(
            LONG_LINE
            + "\n"
            + command
            + "\n"
            + LONG_LINE
            + "\n"
        );
    }

    public static void printGoodbye() {
        System.out.println(
            LONG_LINE
            + "\n"
            + BYE_STR
            + "\n"
            + LONG_LINE
            + "\n"
        );
    }

    public static void printGreeting() {
        System.out.println(
            LONG_LINE
            + "\n"
            + GREETING
            + "\n"
            + LONG_LINE
            + "\n"
        );
    }
}
