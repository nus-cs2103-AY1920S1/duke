package duke;

import java.util.Scanner;

public class Ui {

    private Scanner sc = new Scanner(System.in);

    /**
     * Creates a new Ui object.
     */
    public Ui() {
        //printOutput("Hello! I'm Duke\nWhat can i do for you?");
    }

    public static String getGreeting(){
        return "Hello! I'm Duke\nWhat can i do for you?";
    }

    /**
     * Reads the next command from the console.
     *
     * @return Command read from the console.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints the exit string to the console.
     */
    public void printExit() {
        printOutput("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the string specified to the console.
     *
     * @param s Message to be included in the output.
     */
    public void printOutput(String s) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    " + s.replace("\n", "\n    "));
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints the string specified to the console.
     *
     * @param s           Message to be included in the output.
     * @param taskMessage Task Message to be included in the output.
     * @param listSize    Size of the list.
     */
    public void printOutput(String s, String taskMessage, int listSize) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    " + taskMessage);
        System.out.println("    " + s.replace("\n", "\n    "));
        System.out.println("    " + "Now you have " + listSize + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }

    public static String returnOutput(String s){
        return s.replace("\n", "\n    ");
    }

    public static String returnOutput(String s, String taskMessage, int listSize) {
        return taskMessage + "\n" + s.replace("\n", "\n    ") + "\n" + "Now you have " + listSize + " tasks in the list.";
    }
}
