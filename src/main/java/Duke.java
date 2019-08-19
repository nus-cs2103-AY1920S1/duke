import java.util.Scanner;

public class Duke {
    private static final String border = "____________________________________________________________";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n"
                + border + "\n\nHello! I'm Duke\n" + "What can I do for you?\n" + border + "\n");

        while (true) {
            String str = sc.next();
            if (!str.equals("bye")) {
                System.out.println(echo(str));
            } else {
                break;
            }
        }

        System.out.println(border + "\n\nBye. Hope to see you again soon!" + "\n" + border);

    }

    public static String echo(String string) {
        return border + "\n\n" + string + "\n" + border + "\n";
    }
}
