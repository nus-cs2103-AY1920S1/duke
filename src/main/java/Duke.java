import java.util.Scanner;

public class Duke {
    private static final String LOGO =
            " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String TOP_SEPARATOR =
            "\t____________________________________________________________\n";
    private static final String BOTTOM_SEPARATOR =
            "\t____________________________________________________________\n";
    private static final String GREET_MESSAGE =
            "\tHello! I'm Duke. What can I do for you?\n";
    private static final String EXIT_MESSAGE =
            "\tBye. Hope to see you again soon!\n";

    public void run() {
        System.out.println(LOGO + TOP_SEPARATOR + GREET_MESSAGE + BOTTOM_SEPARATOR);

        Scanner sc = new Scanner(System.in);
        while (true) {
            String cmd = sc.next();
            switch (cmd) {
                case "bye":
                    System.out.println(TOP_SEPARATOR + EXIT_MESSAGE + BOTTOM_SEPARATOR);
                    return;
                default:
                    System.out.println(TOP_SEPARATOR + "\t" + cmd + "\n" + BOTTOM_SEPARATOR);
                    break;
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
