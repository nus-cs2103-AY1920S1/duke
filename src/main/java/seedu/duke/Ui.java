package seedu.duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Ui is a class that deals with interactions with the user. It contains operations that
 * read commands by the users and prints messages.
 */
public class Ui {

    /**
     * Constructor of the Ui class.
     */
    public Ui() {
    }

    /**
     * Prints the greeting message.
     */
    public static void greet() {
        String message = "Hello! I'm Duke\n"
                + "What can I do for you?";
        System.out.println(message);
    }

    /**
     * Scans the command and return it.
     *
     * @return the command that the user input
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Returns the separating line.
     *
     * @return the separating line
     */
    public String showLine() {
        return "____________________________________________________________";
    }

    /**
     * Prints the exit message.
     */
    public static void exit() {
        String byeMessage = "Bye. Hope to see you again soon!";
        System.out.println(byeMessage);
    }

    /**
     * Prints the error message if the storage cannot load.
     */
    public static void showLoadingError() {
        System.out.println("Storage cannot be loaded!");
    }

    /**
     * Prints message and all the tasks in the list.
     *
     * @param list the list of tasks
     */
    public static void printList(TaskList list) {
        System.out.println("Here are the tasks in your list:");
        for (int j = 0; (j < list.getSize()) && list.getTask(j) != null; j++) {
            System.out.println(j + 1 + "." + list.getTask(j));
        }
    }

    /**
     * Prints the add message.
     */
    public static void printAddMsg() {
        System.out.println("Got it. I've added this task:");
    }

    /**
     * Prints the message stating the number of tasks in the list.
     *
     * @param list the list of tasks
     */
    public static void printNumTask(TaskList list) {
        System.out.println("Now you have " + list.getSize() + " tasks in the list.");
    }

    /**
     * Prints the remove message.
     */
    public static void printRemoveMsg() {
        System.out.println("Noted. I've removed this task:");
    }

    /**
     * Prints the latest task in the list.
     *
     * @param list the list of tasks
     */
    public static void printLatest(TaskList list) {
        System.out.println(list.getTask(list.getSize() - 1));
    }

    /**
     * Prints the matching message.
     */
    public static void printMatchingMsg() {
        System.out.println("Here are the matching tasks in your list:");
    }

    /**
     * Prints the tasks in the matching list.
     *
     * @param list the list of matching tasks
     */
    public static void printMatchingList(ArrayList<Task> list) {
        for (int j = 0; (j < list.size()) && list.get(j) != null; j++) {
            System.out.println(j + 1 + "." + list.get(j));
        }
    }

    /**
     * Returns the empty description error message.
     *
     * @param s the type of task
     * @return the empty description error message
     */
    public static String emptyDescriptionMsg(String s) {
        return ("☹ OOPS!!! The description of a " + s + " cannot be empty.");
    }

    /**
     * Returns the invalid input error message.
     *
     * @return the invalid input error message
     */
    public static String invalidInputMsg() {
        return ("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Prints the error message.
     *
     * @param s the error message
     */
    public void printErrMsg(String s) {
        System.out.println(s);
    }

}
