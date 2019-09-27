package duke.execution;

import java.io.IOException;
import java.util.Scanner;

public class Ui {

    protected Scanner scan = new Scanner(System.in);

    /**
     * Constructor for Ui.
     */
    public Ui() {

    }

    /**
     * Prints the greeting at the initiation of the chat bot.
     */
    public String greeting() {
        String dukeLogo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";

        // Prints out greeting of the chat bot.
        String logo = "    Hello! My name is \n" + dukeLogo + "\n"
                + "    What can I do for you? \n";
        printIndent();
        logo += "    I can only do these functions for now: \n \n"
                + "    Todo \n" + "        Eg. todo __(task)__\n"
                + "    Event \n" + "        Eg. event __(task)__\n"
                + "            /at _(dd/MM/yyyy)_(hhmm)__\n"
                + "    Deadline \n" + "        Eg. deadline __(task)__\n"
                + "            /by _(dd/MM/yyyy)_(hhmm)__\n"
                + "    Delete \n" + "        Eg. delete __(number)__ or delete all\n"
                + "    Done \n" + "        Eg. done __(number)__\n"
                + "    Find \n" + "        Eg. find __(keyword)__\n"
                + "    Expenses \n" + "        Eg. expenses __$(amount)__\n"
                + "            /on __(category)__\n"
                + "    List \n" + "    Help\n" + "    Bye\n";
        //printIndent();
        //System.out.println("Ill be adding in more features soon! Please be patient! :)");
        return logo;
    }

    /**
     * Prints indentation.
     * Helps to order the output, making it much neater.
     */
    public static void printIndent() {
        System.out.print("    ");
    }

    /**
     * Prints line.
     * Helps to order the output and makes it
     * much neater.
     */
    public static void printLine() {
        printIndent();
        System.out.println("_____________________________"
                + "______________________________________");
    }

    /**
     * Prints a statement informing the user that the bot
     * has added the task into the list.
     */
    public String printGI() {
        return "Got it. I've added this task:";
    }

    /**
     * Ends the chat bot.
     */
    public String printBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Reads what the user writes.
     *
     * @return Returns what the scanner reads.
     */
    public String readCommand() {
        return scan.nextLine();
    }

    /**
     * To remove a task if it is not needed anymore.
     *
     * @param i Indicates the task number that is done.
     */
    public static String printDelete(int i) {
        return CompleteList.listOfPlans.get(i - 1).toString();
    }

    /**
     * Prints a statement to tell the user that the task has been removed.
     */
    public String printRemove() {
        return "Noted. I've removed this task.";
    }

    /**
     * Prints the number of tasks in the list.
     *
     * @throws IOException If the named file exists but is a directory rather than a regular file,
     *     does not exist but cannot be created, or cannot be opened for any other reason.
     */
    public static String printNumOfTasks() throws IOException {
        return "Now you have " + Ui.getNumOfTasks() + " tasks in the list.";
    }

    /**
     * Gets the number of task inside the file.
     *
     * @return Number of tasks.
     * @throws IOException If the named file exists
     *     but is a directory rather than a regular file,
     *     does not exist but cannot be created, or
     *     cannot be opened for any other reason.
     */
    public static int getNumOfTasks() throws IOException {
        return Storage.countLines(Storage.file);
    }

    /**
     * When there is nothing in the file, this method
     * will print out to the user, telling them that there
     * is no previous tasks saved in the file.
     */
    public void showLoadingError() {
        printLine();
        printIndent();
        System.out.println("Nothing in file!");
    }

    /**
     * Prints out the error message.
     *
     * @param error Error message.
     */
    public void showError(String error) {
        assert error != null;
        System.out.println(error);
    }

    /**
     * Prints out a String that informs the user what functions
     * are available on this application.
     *
     * @return Returns a String that lists out all the functions.
     */
    public String printHelp() {
        printLine();
        printIndent();
        return "    I can only do these functions for now: \n \n"
                + "    Todo \n" + "        Eg. todo __(task)__\n"
                + "    Event \n" + "        Eg. event __(task)__\n"
                + "            /at _(dd/MM/yyyy)_(hhmm)__\n"
                + "    Deadline \n" + "        Eg. deadline __(task)__\n"
                + "            /by _(dd/MM/yyyy)_(hhmm)__\n"
                + "    Delete \n" + "        Eg. delete __(number)__ or delete all\n"
                + "    Done \n" + "        Eg. done __(number)__\n"
                + "    Find \n" + "        Eg. find __(keyword)__\n"
                + "    Expenses \n" + "        Eg. expenses __$(amount)__\n"
                + "            /on __(category)__\n"
                + "    List \n" + "    Help\n" + "    Bye\n";
    }
}