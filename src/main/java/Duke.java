import java.util.Scanner;
import java.util.ArrayList;

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
        ArrayList<String> list = new ArrayList<>(100);

        while (run) {
            String command = scanner.nextLine();

            if (command.equals("bye")) {
                run = false;
                command = "\tBye. Hope to see you again soon!";
            } else if (command.equals("list")) {
                int i = 1;
                System.out.println(divider);
                for (String s: list) {
                    System.out.println("\t" + i + ". " + s);
                    i++;
                }
                System.out.println(divider);
                continue;
            } else {
                list.add(command);
                command = "\tadded: " + command;
            }

            System.out.println(divider);
            System.out.println(command);
            System.out.println(divider);
        }
    }
}
