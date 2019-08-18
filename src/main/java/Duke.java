import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // Welcome message
        System.out.println("\t____________________________________________________________");
        System.out.println("\n\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________\n");

        // Echo commands until bye is entered

        while (true) {
            String command = sc.next();

            if (command.equals("bye")) {
                System.out.println("\t____________________________________________________________");
                System.out.println("\n\tBye. Hope to see you again soon!");
                System.out.println("\t____________________________________________________________\n");
                break;
            } else {
                System.out.println("\t____________________________________________________________");
                System.out.println("\n\t" + command);
                System.out.println("\t____________________________________________________________\n");
            }
        }

        sc.close();

    }
}
