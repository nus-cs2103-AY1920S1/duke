package duke.ui;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner scan = new Scanner(System.in);

    /**
     * Prints welcome message for user.
     */
    public void printIntro() {
        String logo = "\t ____        _        \n"
                + "\t |  _ \\ _   _| | _____ \n"
                + "\t | | | | | | | |/ / _ \\\n"
                + "\t | |_| | |_| |   <  __/\n"
                + "\t |____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\t Hello from\n" + logo);
        printLine();
        printDuke("Hello! I'm Duke\n");
        printDuke("What can I do for you?\n");
        printLine();
    }

    /**
     * Prints line that encloses Duke's dialogues.
     */
    public void printLine() {
        System.out.println("\t____________________________________________________________");
    }

    /**
     * Scans command from user input.
     */
    public String scanCmd() {
        return scan.nextLine();
    }

    /**
     * Prints messages from Duke.
     *
     * @param toPrint string to be printed
     */
    public void printDuke(String toPrint) {
        System.out.print("\t" + toPrint);
    }

    /**
     * Prints a list in Duke format.
     *
     * @param list list containing tasks
     */
    public void printList(ArrayList<Task> list) {
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            System.out.println("\t" + (i + 1) + ". " + task.toString());

        }
    }

    /**
     * Prints error messages.
     *
     * @param error the type of error
     */
    public void printError(String error) {
        printDuke(error);
    }

}
