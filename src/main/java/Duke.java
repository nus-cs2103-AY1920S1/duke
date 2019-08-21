import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");

        Scanner sc = new Scanner(System.in);
        echo(sc);
    }

    public static void echo(Scanner sc) {
        while(sc.hasNext()) {
            String command = sc.next();
            if (command.equals("bye")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println("    ____________________________________________________________");
                return;
            } else {
                System.out.println("    ____________________________________________________________");
                System.out.println("     " + command);
                System.out.println("    ____________________________________________________________");
            }
        }
    }

}
