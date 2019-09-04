import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Create a scanner to take in user input
        Scanner sc = new Scanner(System.in);

        System.out.println(
                formatMessage("Hello, I'm Duke\nWhat can I do for you?")
        );

        String command = sc.nextLine();

        while (!command.equals("bye")) {
            System.out.println(
                    formatMessage(command)
            );
            command = sc.nextLine();
        }

        System.out.println(
                formatMessage("Bye. Hope to see you again soon!")
        );
    }

    private static String formatMessage(String message) {
        String formatted = "    ____________________________________________________________\n";
        String[] lines = message.split("\n");
        for (String line : lines) {
            formatted += "     " + line + "\n";
        }
        formatted += "    ____________________________________________________________\n";

        return formatted;
    }
}
