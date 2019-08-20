import java.util.Scanner;

public class Duke {
    public static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Echoes user input without filtering.
     * @param args  Standard args
     */
    public static void main(String[] args) {
        DukeFormatter.prettyPrint("Hello! I'm Duke\nWhat can I do for you?");
        while (scanner.hasNext()) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) { break; }
            DukeFormatter.prettyPrint(userInput);
        }
        DukeFormatter.prettyPrint("Bye. Hope to see you again soon!");
    }
}
