import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printGreeting();

        while (scanner.hasNext()) {
            String command = scanner.nextLine();

            if (command.equals("bye")) {
                printExitMessage();
                break;
            }

            printSeparator();
            System.out.println(" " + command);
            printSeparator();
        }
    }

    public static void printSeparator() {
        System.out.println("____________________________________________________________");
    }

    public static void printGreeting() {
        printSeparator();
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        printSeparator();
    }

    public static void printExitMessage() {
        printSeparator();
        System.out.println(" Bye. Hope to see you again soon!");
        printSeparator();
    }
}
