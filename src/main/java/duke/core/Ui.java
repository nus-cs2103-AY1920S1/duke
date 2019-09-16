package duke.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.tasks.Task;


/**
 * Represents the user interface of the application. Provides methods for reading input
 * and printing output to the console.
 */
public class Ui {

    final private static String horizontalLine = "    ____________________________________________________________";

    private Scanner sc = new Scanner(System.in);


    /**
     * Checks if there are inputs to be read
     *
     * @return true if there are inputs, false otherwise
     */
    public boolean hasInputs() {
        return sc.hasNextLine();
    }


    /**
     * Reads from standard input and returns it.
     *
     * @return String which consists of user input.
     */
    public String readCommand(){
        return sc.nextLine();
    }

    /**
     * Prints the welcome message of the application.
     */
    public void printWelcomeMessage() {
        List<String> start = new ArrayList<>();
        start.add("Hello! I'm Duke");
        start.add("What can I do for you?");
        this.printInput(start);
    }



    /**
     * Prints a message when a task is added.
     *
     * @param task Task that was added.
     * @param taskList Task list where the Task is stored.
     */
    public void printAddMessage(Task task, TaskList taskList) {
        System.out.println(horizontalLine);

        System.out.println("     Got it. I've added this task: ");
        System.out.println(String.format("       %s",task));
        System.out.println(String.format("     Now you have %d tasks in the list.", taskList.getNumTasks()));
        System.out.println(horizontalLine);
        System.out.println();

    }

    /**
     * Prints out the message when a task is deleted.
     *
     * @param task Task that was deleted.
     * @param taskList Task list that the Task was removed from.
     */
    public void printDeletion(Task task, TaskList taskList) {
        System.out.println(horizontalLine);

        System.out.println("     Noted. I've removed this task: ");
        System.out.println(String.format("       %s",task));
        System.out.println(String.format("     Now you have %d tasks in the list.",taskList.getNumTasks()));
        System.out.println(horizontalLine);
        System.out.println();

    }


    /**
     * Prints a list of string in the correct format
     *
     * @param start List of Strings which needs to be printed sequentially
     */
    public void printInput(List<String> start) {
        System.out.println(horizontalLine);
        for (String input : start) {
            System.out.println(String.format("     %s",input));
        }

        System.out.println(horizontalLine);
        System.out.println();
    }

    /**
     * Prints a single line of input in the correct format
     *
     * @param input String to be printed
     */
    public void printOneLine(String input) {
        System.out.println(horizontalLine);
        System.out.println(String.format("     %s",input));
        System.out.println(horizontalLine);
        System.out.println();

    }


    /**
     * Prints the list of tasks.
     *
     * @param taskList List of tasks stored in the application.
     */
    public void printNumberList(TaskList taskList) {
        System.out.println(horizontalLine);
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < taskList.getNumTasks(); i++) {
            System.out.println(String.format("     %d.%s",i+1, taskList.getTaskAt(i+1)));
        }

        System.out.println(horizontalLine);
        System.out.println();
    }


    /**
     * Prints the exit message.
     */
    public void printByeMessage() {
        this.printOneLine("Bye. Hope to see you again soon!");
    }


    /**
     * Prints out the exception.
     * @param e Exception object whose message is to be printed out
     */
    public void printErrorMessage(Exception e) {
        this.printOneLine(e.getMessage());
    }

    
    
}

