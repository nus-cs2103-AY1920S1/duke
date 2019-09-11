
import tasks.Todo;

import java.util.Scanner;
import java.util.Date;
import java.text.ParseException;

/**
 * Handles the user command.
 */
public class Parser {
    private Scanner sc;

    public Parser(Scanner sc) {
        this.sc = sc;
    }

    /**
     * Gets the next command from user input.
     * @return The next line of command
     */
    public String nextCommand() {
        return sc.nextLine();
    }

    /**
     * Separates the command and description.
     * @param str The command string
     * @return A string array with command and description separated
     */
    public String[] breakDownCommand(String str) {
        return str.split(" ");
    }

    /**
     * Separates the description and date.
     * @param str The command string
     * @return A string array with description and date separated
     */
    public String[] breakDownDescription(String str) {
        return str.split("/");
    }

    /**
     * Gets the command of the task.
     * @param arr A string array with command and description separated
     * @return The command string
     */
    public String getCommand(String[] arr) {
        return arr[0];
    }

    /**
     * Gets the todo based on the command line
     * @param line The command list
     * @return The todo
     */
    public Todo getTodo(String line) {
        String task = line.substring(5);
        Todo todo = new Todo(task);
        return todo;
    }

    /**
     * Gets the description of the deadline.
     * @param descArr The string array with command and description separated
     * @return The description of the deadline
     */
    public String getDeadlineDesc(String[] descArr) {
        return descArr[0].substring(9, descArr[0].length() - 1);
    }

    /**
     * Gets the description of the event.
     * @param descArr The string array with command and description separated
     * @return The description of the event
     */
    public String getEventDesc(String[] descArr) {
        return descArr[0].substring(6, descArr[0].length() - 1);
    }

    /**
     * Gets the date.
     * @param descArr The command line with description and date separated
     * @param storage The storage for the task manager
     * @return The date
     * @throws ParseException If a parse exception occurred
     */
    public Date getDate(String[] descArr, Storage storage) throws ParseException {
        return storage.convertToDate(descArr[1].substring(3));
    }

    /**
     * Gets the task number.
     * @param arr The array with command and description separated
     * @return The task number
     */
    public int getTaskNum(String[] arr) {
        return Integer.parseInt(arr[1]) - 1;
    }
}