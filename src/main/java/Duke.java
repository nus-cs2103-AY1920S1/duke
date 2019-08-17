import java.util.ArrayList;
import java.util.Scanner;

/**
 * Duke Chat Class.
 *
 * A Personal Assistant Chatbot that helps to keep track of various things.
 *
 * @author Marcus Ong
 */
public class Duke {

    private static ArrayList<String> messages = new ArrayList<>();

    public static void main(String[] args) {
        chat();
    }

    /** Handles user chat input */
    public static void chat() {
        // Create default hi/bye strings
        String logo = " ____        _        \n\t"
                + "|  _ \\ _   _| | _____ \n\t"
                + "| | | | | | | |/ / _ \\\n\t"
                + "| |_| | |_| |   <  __/\n\t"
                + "|____/ \\__,_|_|\\_\\___|\n\t";
        String greeting = logo + "Hello! I'm Duke\n\tWhat can I do for you?";
        String bye = "Bye. Hope to see you again soon!";

        reply(greeting);

        // Begin chat interaction
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equalsIgnoreCase("bye")) {
            if (input.equalsIgnoreCase("list")) {
                displayList();
            } else {
                addToList(input);
            }
            input = sc.nextLine();
        }

        reply(bye);
    }

    /** Add input to list of messages */
    private static void addToList(String input) {
        messages.add(input);
        reply("added: " + input);
    }

    /** Display list of messages */
    private static void displayList() {
        StringBuilder listStringBuilder = new StringBuilder();

        for (int i = 0; i < messages.size(); i++) {
            listStringBuilder.append((i+1) + ". " + messages.get(i) + "\n\t");
        }

        reply(listStringBuilder.toString());
    }

    /**
     * Prints out reply message to the user.
     *
     * @param message String containing reply message.
     */
    public static void reply(String message) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t" + message);
        System.out.println("\t____________________________________________________________");
    }
}
