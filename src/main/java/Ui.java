/**
 * A class to handle User Interactions.
 */

import java.util.Scanner;

public class Ui {

    /**
     * A method that prints out the introduction when Duke first initialises.
     */

    public String showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        logo += "Hello from\n" + logo + "Hello! I'm Duke \nWhat can I do for you?";
        return logo;
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public String printTask(int count, Task t) {
        assert count > 0 : "Number of tasks cannot be negative.";
        if (count == 1) {
            return "  " + t + "\nNow you have " + count + " task in the list.";
        } else {
            return "  " + t + "\nNow you have " + count + " tasks in the list.";
        }
    }

}
