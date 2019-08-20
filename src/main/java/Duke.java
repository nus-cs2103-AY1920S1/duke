import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        printMessage("Hello! I'm Duke\nWhat can I do for you?");
        while (true) {
            String input = in.nextLine();
            if (!input.equals("bye")) {
                printMessage(input);
            } else {
                printMessage("Bye. Hope to see you again soon!");
                break;
            }
        }
        in.close();
    }

    static void printMessage(String message) {
        String[] messages = message.split("\n");
        System.out.println("    ____________________________________________________________");
        for (String line : messages) {
            System.out.println("     " + line);
        }
        System.out.println("    ____________________________________________________________");
        System.out.println();
    }
}
