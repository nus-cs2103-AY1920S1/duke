package duke.util;

import java.util.Scanner;
import java.lang.Exception;
import duke.task.Task;

public class Ui {
    private Scanner sc;

    /**
     * Constructor.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads command from System.in.
     *
     * @return String
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Generic print.
     *
     * @param str String
     */
    public void print(String str) {
        System.out.println(str);
    }

    /**
     * Pretty prints the output to the user with indentation of 4.
     *
     * @param str the String to be printed
     */
    public void prettyPrint4(String str) {
        System.out.printf("%s", prepend4(str));
    }

    /**
     * Pretty prints the output to the user with indentation of 4.
     *
     * @param str the String to be printed 
     */
    public void prettyPrint6(String str) {
        System.out.printf("%s", prepend6(str));
    }

    /**
     * Insert 4 spaces into String at front.
     *
     * @param str String
     * @return String
     */
    public String prepend4(String str) {
        return String.format("    %s\n", str);
    }

    /**
     * Insert 6 spaces into String at front.
     *
     * @param str String
     * @return String
     */
    public String prepend6(String str) {
        return String.format("      %s\n", str);
    }

    /**
     * Generate task acknowledgement.
     *
     * @param task Task
     * @param size int the size of the task list
     * @return String
     */
    public String genAddTaskAck(Task task, int size) {
        return String.format("%s%s%s",
                prepend4("Got it. I've added this task:"),
                prepend6(task.toString()),
                prepend4(String.format("Now you have %d tasks in the list.", size)));
    } 

    /**
     * Sends task acknowledgement.
     *
     * @param task Task
     * @param size int the size of the task list
     */
    public void sendAddTaskAck(Task task, int size) {
        System.out.printf("%s", genAddTaskAck(task, size));
    } 

    /**
     * Generate welcome message.
     *
     * @return String
     */
    public String genWelcome() {
        return "Hello I'm Dog\nWhat can I do for you\n";
    }

    /**
     * Generate bye message.
     *
     * @return String
     */
    public String genBye() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Generate Write error message.
     *
     * @return String
     */
    public String genWriteError() {
        return "Sorry, failed to write to the disc\n";
    }

    /**
     * Generate read error message.
     *
     * @return String
     */
    public String genReadError() {
        return "Sorry, failed to read from the disc. If it's your first time running this and you haven't used a command that would save things, ignore as the save file has not been created. This is because Duke dog only creates the save file once you need something to be saved and if it is not there.\n"; 
    }

    /**
     * Print welcome message.
     */
    public void printWelcome() {
        System.out.printf("%s", genWelcome());
    }

    /**
     * Print bye message.
     */
    public void printBye() {
        System.out.printf("%s", genBye());
    }

    /**
     * Print write error.
     */
    public void printWriteError() {
        System.out.printf("%s", genWriteError());
    }

    /**
     * Print read error.
     */
    public void printReadError() {
        System.out.printf("%s", genReadError());
    }

    /**
     * Error in string form.
     *
     * @return String
     */
    public String stringifyError(Exception e) {
        return String.format("%s\n", e.toString()); 
    }

    /**
     * Print error.
     *
     * @param e Exception
     */
    public void printError(Exception e) {
        System.out.printf("%s", stringifyError(e));
    }
}
