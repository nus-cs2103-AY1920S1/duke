package duke.core;

import duke.task.Task;
import java.util.Scanner;
import java.util.ArrayList;

public class Ui {
    Scanner scanner;

    /**
     * Constructor for User Interface Ui and creates one scanner everytime it starts up.
     */
    public Ui() {
        Scanner scanner = new Scanner(System.in);
        this.scanner = scanner;
    }

    /**
     * Prints out greeting message when Duke first starts up.
     */
    public void printHello() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");
    }

    /**
     * Reads user input. Duke class calls this method to read user's input.
     *
     * @return input which is a String that is read from the user's input which will later be passed to the Parser.
     */
    public String readCommand() {
        String input = scanner.nextLine();
        return input;
    }

    /**
     * Prints the list of tasks in TaskList. ListCommand class calls this function.
     *
     * @param textEntered is retrieved from TaskList class.
     */
    public void printRecord(ArrayList<Task> textEntered) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < textEntered.size(); i++) {
            System.out.print((i + 1) + "." + textEntered.get(i).toString() + "\n");
        }
        System.out.println();
    }

    /**
     * Prints message when Duke ends as ExitCommand is called.
     */
    public void printBye() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    /**
     * Prints message of DukeException when exception is thrown.
     */
    public void printError(String excepMsg) {
        System.out.println(excepMsg + "\n");
    }

    /**
     * Prints notification that the task has been added.
     */
    public void printAdd(Task t, int sizeOfTask) {
        System.out.printf("Got it. I've added this task: \n  %s\nNow you have %d tasks in the list.%n", t.toString(),
                sizeOfTask);
        System.out.println();
    }
    /**
     * Prints notification that the task has been done.
     */
    public void printDone(Task t) {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("  " + t.toString() + "\n");
    }

    /**
     * Prints notification that the task has been deleted.
     */
    public void printDelete(Task t, int sizeOfTask) {
        System.out.println("Noted. I've removed this task: \n" + "  " + t.toString() + "\n"
                + "Now you have " + sizeOfTask + " tasks in the list." + "\n");
    }

    /**
     * Prints list of tasks which has been filtered to include the keyword.
     */
    public void printFind(ArrayList<Task> inputList) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < inputList.size(); i++) {
            System.out.print((i + 1) + "." + inputList.get(i).toString() + "\n");
        }
        System.out.println();
    }

}
