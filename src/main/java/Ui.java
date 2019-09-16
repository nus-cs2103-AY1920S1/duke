import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    protected Scanner scan = new Scanner(System.in);
    protected TaskList listTask = new TaskList();
    protected Storage store = new Storage(Storage.file);

    /**
     * Constructor for Ui.
     */
    public Ui() {

    }

    public String readInput() {
        return scan.nextLine();
    }
    /**
     * Prints the greeting at the initiation of the chat bot.
     */
    public void greeting() {
        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";

        // Prints out greeting of the chat bot.
        printLine();
        printIndent();
        System.out.println("Hello! My name is \n" + logo + "\n"
                + "    What can I do for you? \n");
        printIndent();
        System.out.println("I can only do these functions for now: \n \n"
                + "    Todo \n" + "        Eg. todo __(task)__\n"
                + "    Event \n" + "        Eg. event __(task)__ /at _(dd/MM/yyyy)_(hhmm)__\n"
                + "    Deadline \n" + "        Eg. deadline __(task)__ /by _(dd/MM/yyyy)_(hhmm)__\n"
                + "    Delete \n" + "        Eg. delete __(number)__ or delete all\n"
                + "    Done \n" + "        Eg. done __(number)__\n"
                + "    Find \n" + "        Eg. find __(keyword)__\n"
                + "    List \n" + "    Bye\n");
        printIndent();
        System.out.println("Ill be adding in more features soon! Please be patient! :)");
        printLine();
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
        Ui.printIndent();
        return "Got it. I've added this task:";
    }

    /**
     * Ends the chat bot.
     */
    public String printBye() {
        printIndent();
        return "Bye. Hope to see you again soon!";
    }

    public String readCommand() {
        return scan.nextLine();
    }

    /**
     * Marks a task as done once the user has finished it.
     *
     * @param i To indicate which task number is done.
     */
    static String printDone(int i) {
        TaskList.listOfTasks.get(i - 1).markAsDone();
        printLine();
        printIndent();
        System.out.println("Nice! I've marked this task as done:");
        printIndent();
        return TaskList.listOfTasks.get(i - 1).toString();
        //printLine();
    }

    /**
     * To remove a task if it is not needed anymore.
     *
     * @param i Indicates the task number that is done.
     */
    public static String printDelete(int i) {
        printIndent();
        return TaskList.listOfTasks.get(i - 1).toString();
        //printLine();
    }

    /**
     * Prints a statement to tell the user that the task has been removed.
     */
    public String printRemove() {
        Ui.printIndent();
        return "Noted. I've removed this task.";
    }
     /**
     * Prints the number of tasks in the list.
     *
     * @throws IOException If the named file exists but is a directory rather than a regular file,
     *     does not exist but cannot be created, or cannot be opened for any other reason.
     */
    public static String printNumOfTasks() throws IOException {
        Ui.printIndent();
        return "Now you have " + Ui.getNumOfTasks() + " tasks in the list.";
        //Ui.printLine();
    }

    /**
     * Prints the list of tasks that has been added by the user.
     *
     * @throws FileNotFoundException if there is no such file that contains the tasks.
     */
    static String printList() throws FileNotFoundException { //WHY NEVER USEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
        printLine();
        printIndent();
        if (TaskList.listOfTasks.isEmpty()) {
            return "There is no tasks in your list currently!!!";
        } else {
            System.out.println("Here are the tasks in your list:");
            File temp = new File(Storage.file);
            Scanner s = new Scanner(temp);
            int numbering = 1;
            while (s.hasNext()) {
                printIndent();
                System.out.println(numbering + ". " + s.nextLine());
                numbering++;
            }
            return "";
        }
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

    public void showError(String error) {
        System.out.println(error);
    }
}