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
        sendGreet();
        sendLine();

        boolean isTerminated = false;

        while (!isTerminated) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                isTerminated = true;
            } else {
                sendLine();
                sendMessage(input);
                sendLine();
            }
        }

        sendLine();
        sendBye();
        sendLine();
    }

    public static void sendLine() {
        System.out.println("____________________________________________________________");
    }

    public static void sendGreet() {
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
    }

    public static void sendBye() {
        System.out.println(" Bye. Hope to see you again soon!");
    }

    public static void sendMessage(String input) {
        System.out.println(" " + input);
    }

}
