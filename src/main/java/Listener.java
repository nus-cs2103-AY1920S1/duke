import java.util.Scanner;

public class Listener {
    public static final String LINE = "    ____________________________________________________________\n";
    public static final String INDENT = "    ";
    public static final String GOODBYE = "Bye. Hope to see you again soon!";
    public static final String WELCOME = LINE + INDENT + "Hello! I'm Duke\n" + INDENT + "What can I do for you?\n"
                                        + LINE;

    public void start() {

        Scanner sc = new Scanner(System.in);

        System.out.print(WELCOME);
        while(sc.hasNext()) {
            String command = sc.next();
            if (command.equals("bye")) {
                System.out.print(LINE + INDENT + GOODBYE + "\n" + LINE);
                break;
            } else {
                switch (command) {
                    default:
                        System.out.print(LINE + INDENT + command + "\n" + LINE);
                        break;
                }
            }
        }
    }
}
