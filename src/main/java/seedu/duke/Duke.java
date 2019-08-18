package seedu.duke;

import java.util.Scanner;

/**
 * Represents the Duke application object.
 */
public class Duke {
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
            String input = sc.next();
            if (input.equals("bye")) {
                System.out.println(farewellText);
                break;
            } else {
                // Echos commands entered by the user.
                System.out.println(input);
            }
        } // end while loop

        sc.close();
    }
}
