import java.util.Scanner;

public class Duke {
    private static String input;
    private static boolean running;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        greet();
        while (running) {
            readInput();
            processInput();
        }
    }

    public static void greet() {
        running = true;
        System.out.println(
                "    ____________________________________________________________\n" +
                        "     Hello! I'm Duke\n" +
                        "     What can I do for you?\n" +
                        "    ____________________________________________________________\n");
    }

    public static void readInput() {
        Scanner sc = new Scanner(System.in);
        input = sc.next();
    }

    public static void processInput() {
        switch (input) {
            case "bye":
                System.out.println(
                        "    ____________________________________________________________\n" +
                                "     Bye. Hope to see you again soon!\n" +
                                "    ____________________________________________________________"
                );
                running = false;
                break;

            default:
                System.out.println(
                        "    ____________________________________________________________\n" +
                                String.format("     %s\n", input) +
                                "    ____________________________________________________________\n"
                );
        }
    }
}
