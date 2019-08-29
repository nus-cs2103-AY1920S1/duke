package duke.utils;

import duke.exceptions.DukeException;

import java.util.Scanner;

/**This class is meant to abstract away all details of interacting with the user via
 * the console. It provides convenience methods for dealing with both input and output.
 */
public class Ui {
    Scanner sc;

    /**Constructor*/
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**Reads in a single line from console input*/
    public String readLine() {
        return sc.nextLine();
    }

    /**Prints a welcome message to console*/
    public void printWelcomeMsg() {
        printLine();
        System.out.println("\tHello! I'm Duke!");
        System.out.println("\tWhat can I do for you?");
        printLine();
    }

    /**Prints an exit message to console*/
    public void printExitMsg() {
        printLine();
        System.out.println("\tBye! Hope to see you again soon!");
        printLine();
    }

    /**Prints an error message to console*/
    public void printErrorMsg(DukeException e) {
        printErrorLine();
        System.out.println("\t" + e.getMessage());
        printErrorLine();
    }

    /**Prints an input sentence to console*/
    public void printSentence(String sentence) {
        System.out.println("\t" + sentence);
    }

    /**Prints a line to demarcate different sections*/
    public void printLine() {
        System.out.println("\t____________________________________________________________");
    }

    /**Prints a line to demarcate an error section*/
    public void printErrorLine() {
        System.out.println("\t************************************************************");
    }
}
