import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        initialize();
        chat();
    }

    public static void chat() {
        String greeting = "\tHello! I'm Duke\n\tWhat can I do for you?";
        String bye = "\tBye. Hope to see you again soon!";

        reply(greeting);
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        while (!input.equalsIgnoreCase("bye")) {
            reply("\t" + input);
            input = sc.next();
        }
        reply(bye);
    }

    public static void initialize() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
    }

    public static void reply(String message) {
        System.out.println("\t____________________________________________________________");
        System.out.println(message);
        System.out.println("\t____________________________________________________________");
    }
}
