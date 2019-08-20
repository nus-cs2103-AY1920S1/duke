import java.util.Scanner;

public class Duke {

    static String divider = "\t____________________________________________________________";
    static String intro = "\tHello! I'm Duke\n\tWhat can I do for you?";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(divider);
        System.out.println(intro);
        System.out.println(divider);

        Scanner scanner = new Scanner(System.in);
        boolean run = true;

        while (run) {
            String command = scanner.nextLine();

            if (command.equals("bye")) {
                run = false;
                command = "Bye. Hope to see you again soon!";
            }
            System.out.println(divider);
            System.out.println("\t" + command);
            System.out.println(divider);
        }
    }
}
