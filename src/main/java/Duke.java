package seedu.duke;

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

    }
}
