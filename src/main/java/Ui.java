import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Handles the printing of Duke messages.
 */
public class Ui {

    final String lineSpace = "_______________________________\n";
    private Scanner sc;
    final String helpFilePath;

    /**
     * Default Constructor to initialise Scanner object for user input.
     */
    public Ui() {
        sc = new Scanner(System.in);
        helpFilePath = "src/main/data/help.txt";
    }

    /**
     * Prints out Welcome Message for the launch of Duke Chatbot.
     *
     * @return welcome message
     */
    public String showWelcome() {
        /*
        final String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
         */
        final String startMessage = "Bark Bark! I'm Duke, Your Task Assistant Dog.\n"
                + "What can I do for you?\n" + "If you need help, type 'help' in the field.";
        return startMessage;
    }

    /**
     * Prints Goodbye Message when user closes the Duke Chatbot.
     *
     * @return goodbye message
     */
    public String showGoodbye() {
        String endMessage = "Woof! Your list has been saved. Hope to see you again!";
        return endMessage;
    }

    /**
     * Prints a specified error message.
     *
     * @param error Message of error
     * @return Message of error
     */
    public String showError(String error) {
        return error;
    }

    /**
     * Prints a message when tasks are added.
     *
     * @param task Task being added
     * @param listSize Size of list
     * @return Message of successful add
     */
    public String showAdd(Task task, int listSize) {
        String message = "Bark! I've added this task:\n" + task
                + "\nNow you have " + listSize + " tasks in the list.";
        return message;
    }

    /**
     * Prints a message when tasks are set to done.
     *
     * @param task Task selected to be done
     * @return Message of marking done
     */
    public String showDone(Task task) {
        String message = "Ruff! I've marked this task as done:\n"
                + task;
        return message;
    }

    /**
     * Prints a message when tasks are deleted.
     *
     * @param task Task selected for deletion
     * @param listSize Size of list
     * @return Message of successful deletion
     */
    public String showDelete(Task task, int listSize) {
        String message = "Arf! I've removed this task:\n" + task
                + "\nNow you have " + listSize + " tasks in the list.";
        return message;
    }

    /**
     * Prints a help message that lists the available commands the user is able
     * to type. Message is read from a text file in /data folder.
     *
     * @return Help Message
     * @throws DukeException If file is unable to be read
     */
    public String showHelp() throws DukeException {
        try {
            File f = new File(helpFilePath);
            Scanner sc = new Scanner(f);
            String helpMessage = sc.useDelimiter("\\A").next();
            sc.close();
            return helpMessage;
        } catch (FileNotFoundException e) {
            throw new DukeException("Failed to load help text file");
        }
    }


    /**
     * Prints a straight line for clarity purposes.
     * Legacy code for Command Line display.
     */
    public void showLine() {
        System.out.print(lineSpace);
    }

    /**
     * Reads the user input and returns a String.
     * Legacy code for Command Line display.
     *
     * @return User input
     */
    public String readCommand() {
        String userCmd = sc.next();
        return userCmd;
    }

    /**
     * Returns the description of Task specified in the user input.
     * Use only after reading the initial command input of the user.
     */
    public String readDesc() {
        String desc = sc.nextLine();
        return desc;
    }

    /**
     * Returns the index of the Task specified in the user input.
     * Use only after reading the initial command input of the user.
     */
    public int readIndex() {
        int index = sc.nextInt();
        return index;
    }
}
