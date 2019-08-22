package seedu.duke;

import java.util.Scanner;
import seedu.duke.PrettyPrint;
import seedu.duke.EventHandler;

/**
 * Main class of this project.
 * Personal assistant bot to help you keep track of tasks.
 */

public class Duke {

    public static void main(String[] args) {

        String logo = " ____        _        \n"
                                + "|  _ \\ _   _| | _____ \n"
                                + "| | | | | | | |/ / _ \\\n"
                                + "| |_| | |_| |   <  __/\n"
                                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        PrettyPrint.printBlock(new String[] {"Hello! I'm Duke.", "What can I do for you?"});

        // Begin input handling
        Scanner sc = new Scanner(System.in);
        String input;
        input = sc.nextLine();
        EventHandler eventHandler = new EventHandler();

        while (!input.equals("bye")) {
            eventHandler.run(input);
            input = sc.nextLine();
        }

        PrettyPrint.printBlock("Bye. Hope to see you again soon!");

    }
}
