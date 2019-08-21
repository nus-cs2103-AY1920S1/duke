import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String welcome = "--------------------------\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "\n"
                + "--------------------------";

        System.out.println(welcome);

        Scanner scanner = new Scanner(System.in);

        while(true) {
            String command = scanner.nextLine();

            if (command.equals("list")) {
                System.out.println("--------------------------\n"
                        + "list\n\n"
                        + "--------------------------" );
            } else if (command.equals("blah")) {
                System.out.println("--------------------------\n"
                        + "blah\n\n"
                        + "--------------------------");
            } else if (command.equals("bye")) {
                System.out.println("--------------------------\n"
                        + "Bye. Hope to see you again soon!\n\n"
                        + "--------------------------");
                break;
            } else {
                System.out.println("--------------------------\n "
                        + "Wrong command! Enter again.\n\n"
                        +  "--------------------------");
            }
        }
    }
}
