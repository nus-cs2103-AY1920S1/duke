import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        sendLine();
        sendGreeting();
        sendLine();

        boolean hasTerminated = false;

        while (!hasTerminated) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                hasTerminated = true;
            } else {
                sendLine();
                sendMessage(input);
                sendLine();
            }
        }

        sendLine();
        sendFarewell();;
        sendLine();

    }

    public static void sendLine() {
        System.out.println("____________________________________________________________");
    }

    public static void sendGreeting() {
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
    }

    public static void sendFarewell() {
        System.out.println(" Bye. Hope to see you again soon!");
    }

    public static void sendMessage(String input) {
        System.out.println(" " + input);
    }
}
