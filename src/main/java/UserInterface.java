import java.util.Scanner;

/**
 * UserInterface class of Duke.
 * In charge of program I/O.
 */
public class UserInterface {
    Scanner sc;

    /**
     * Constructor for UserInterface
     * Initializes with a Scanner object
     */
    public UserInterface(){
        this.sc = new Scanner(System.in);
    }

    /**
     * Print method of Duke
     * @param message message to print
     */
    private void print(String message){
        System.out.println(">>" + message + "\n");
    }

    /**
     * Prints welcome message
     */
    protected void printWelcome(){
        String logo = "\n" +
                " ______       ___    _ .--.   .--.      .-''-.   \n" +
                "|    _ `''. .'   |  | ||  | _/  /     .'_ _   \\  \n" +
                "| _ | ) _  \\|   .'  | || (`' ) /     / ( ` )   ' \n" +
                "|( ''_'  ) |.'  '_  | ||(_ ()_)     . (_ o _)  | \n" +
                "| . (_) `. |'   ( \\.-.|| (_,_)   __ |  (_,_)___| \n" +
                "|(_    ._) '' (`. _` /||  |\\ \\  |  |'  \\   .---. \n" +
                "|  (_.\\.' / | (_ (_) _)|  | \\ `'   / \\  `-'    / \n" +
                "|       .'   \\ /  . \\ /|  |  \\    /   \\       /  \n" +
                "'-----'`      ``-'`-'' `--'   `'-'     `'-..-'   \n" +
                "(◕ᴗ◕✿)\n";
        System.out.println("Hewwo from" + logo);
    }

    /**
     * Prints message when task is successfully added
     * @param task task added
     * @param listSize current number of tasks
     */
    protected void printAdd(Task task, int listSize){
        StringBuilder outputMessage = new StringBuilder("Task added:\n");
        outputMessage.append("    " + task);
        outputMessage.append("\n  There are " + listSize + " tasks in the list.");
        this.print(outputMessage.toString());
    }

    /**
     * Print load save file message
     */
    protected void printLoadSave(){
        this.print("Save file loaded successfully");
    }

    /**
     * Print new file message
     */
    protected void printNewFile(){
        this.print("New save file created");
    }

    /**
     * Print file set to done message
     * @param task
     */
    protected void printDone(Task task){
        this.print("The following task has been marked as done:\n    " + task);
    }

    /**
     * Print file deleted message
     * @param task deleted task
     * @param listSize number of tasks left
     */
    protected void printDelete(Task task, int listSize){
        StringBuilder outputMessage = new StringBuilder("Following task removed:\n    " + task);
        outputMessage.append("\n  " + listSize + " tasks left in the list");
        this.print(outputMessage.toString());
    }

    /**
     * Prints error message
     * @param message error details
     */
    protected void printError(String message){
        this.print("Error: " + message);
    }

    /**
     * Prints list
     * @param list the list duh
     */
    protected void printList(String list){
        this.print("List:" + list);
    }

    /**
     * Reads and return next command input from user
     * @return next user command
     */
    protected String readLine(){
        return sc.nextLine();
    }

    /**
     * Prints exit message
     */
    protected void printExit(){
        this.print("Goodbye. Hope to see you again UwU");
    }
}

