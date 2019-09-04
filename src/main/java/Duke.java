import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    /**
     * Main method of Duke application.
     */
    public static void main(String[] args) {
        // Create a scanner to take in user input
        Scanner sc = new Scanner(System.in);

        System.out.println(
                formatMessage("Hello, I'm Duke\nWhat can I do for you?")
        );

        String command = sc.nextLine();
        ArrayList<String> tasks = new ArrayList<>();

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                String list = "";
                for (int i = 0; i < tasks.size(); i++) {
                    list += String.format(
                            "%d. " + tasks.get(i) + "\n",
                            i + 1
                    );
                }
                System.out.println(formatMessage(list));
            } else {
                tasks.add(command);
                System.out.println(
                        formatMessage("added: " + command)
                );
            }
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
