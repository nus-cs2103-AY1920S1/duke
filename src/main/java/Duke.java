import java.util.Scanner;

public class Duke {
    private static String BREAKLINE = "    ____________________________________________________________";
    private static String GREETING = "     Hello! I'm Duke\n" +
            "     What can I do for you?";
    private static String GOODBYE = "     Bye. Hope to see you again soon!";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        printBreakLine();
        greeting();
        printBreakLine();

        Scanner sc = new Scanner(System.in);
        Logic logic = new Logic(sc);
        int response;
        do {
            System.out.println();
            String command = sc.next();
            printBreakLine();
            response = logic.process(command);
            if (response == -1) {
                goodbye();
            }
            printBreakLine();
        } while (response != -1);
    }

    private static void printBreakLine() {
        System.out.println(BREAKLINE);
    }

    private static void greeting() {
        System.out.println(GREETING);
    }

    private static void goodbye() {
        System.out.println(GOODBYE);
    }
}
