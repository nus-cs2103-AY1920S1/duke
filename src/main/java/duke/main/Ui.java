package duke.main;

import java.util.Scanner;

/**
 * Deals with interaction with the user
 */
public class Ui {
    private String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private String opening = logo + "\nHello! I'm Duke\nWhat can I do for you?";
    private String closing = "Bye. Hope to see you again soon!";

    public String getOpening() {
        return opening;
    }

    public String getClosing() {
       return closing;
    }
}
