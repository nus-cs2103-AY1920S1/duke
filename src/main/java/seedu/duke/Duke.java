package seedu.duke;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Represents the Duke application object.
 */
public class Duke {
    // Class Variables
    protected static List<String> entries = new ArrayList<>(100); // Specification said numTasks < 100.

    /**
     * Runs the Duke application.
     * @param args Stdin.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        */

        String greetingText = "Hello! I'm Duke\nWhat can I do for you?";
        String farewellText = "Bye. Hope to see you again soon!";

        System.out.println(greetingText);

        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(farewellText);
                break;
            } else if (input.equals("list")) {
                listEntries();
            } else {
                entries.add(input);
                System.out.println("added: " + input);
            }
        } // End while loop.

        sc.close();
    } // End of main.

    /**
     * List all entries recorded by Duke; print nothing if no entries are present.
     */
    protected static void listEntries() {
        int numEntry = 1;
        for (String entry : Duke.entries) {
            System.out.printf("%d. %s\n", numEntry, entry);
            numEntry++;
        } // End for loop.
    } // End method.
}
