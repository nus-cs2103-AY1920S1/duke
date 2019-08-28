/**
 * A class to handle User Interactions.
 */

import java.util.Scanner;

public class Ui {

    /**
     * A method that prints out the introduction when Duke first initialises.
     */

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void printTask(int count, Task t) {
        if (count == 1) {
            System.out.println("  " + t + "\nNow you have " + count + " task in the list.");
        } else {
            System.out.println("  " + t + "\nNow you have " + count + " tasks in the list.");
        }
    }

}
