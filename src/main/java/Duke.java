import java.util.Scanner;

public class Duke {
    static final String welcomeMsg = "Hello! I'm Duke\n" +
            "What can I do for you?";
    static final String exitMsg = "Bye. Hope to see you again soon!";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        CmdInterface.printHBars(welcomeMsg);

        boolean isGoodbye = false;
        while (!isGoodbye) {
            String input;
            input = sc.nextLine();
            switch (input) {
                case "bye":
                    CmdInterface.printHBars(exitMsg);
                    isGoodbye = true;
                    break;
                default:
                    CmdInterface.printHBars(input);
            }
        }
    }
}
