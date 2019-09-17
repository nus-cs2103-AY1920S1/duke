package seedu.duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the user interface.
 */
public class Ui {
    /** Scanner for parsing user input.*/
    protected static Scanner sc;
    /** String of a line for formatting.*/
    private static String line = "____________________________________________________________";
    protected static String answer;

    /** Constructor.*/
    public Ui() {
        sc = new Scanner(System.in);
    }

    public static void setAnswer(String ans) {
        answer = ans;
    }

    /**
     * Prints the intro when starting the application.
     */
    public static String intro() {
        String logo = "Hello! \nIs there something I can help you with today?";
        return logo;
    }

    /**
     * Handles the formatting when required.
     * @param s String of content to be printed.
     * @return formatted String.
     */
    public static String format(String s) {
        return line + "\n " + s + "\n" + line;
    }

    /**
     * Prints the line for formatting.
     */
    public static void showLine() {
        System.out.println(line);
    }

    /**
     * Prints error message.
     * @param str String containing error message.
     */
    public static void showError(String str) {
        System.out.println(format(str));
    }

    /**
     * Reads user input.
     * @return String containing user input.
     */
    public static String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints message if there is a problem with loading.
     */
    public static void showLoadingError() {
        System.out.println(format("There seems to be a problem with loading"));
    }

    /**
     * Prints message when marking a task as done.
     * @param str String representing Task done.
     * @return
     */
    public static String doneLine(String str) {
        String message = "Nice! I've marked this task as done:\n " + str;
        return message;
    }

    /**
     * Prints message when deleting a task.
     * @param str String representing Task deleted.
     * @param size integer representing the current size of the TaskList.
     * @return
     */
    public static String deleteLine(String str, int size) {
        String result = "Noted. I've removed this task: \n   "
                + str + "\n Now you have " + size + " tasks in the list.";
        return result;
    }

    /**
     * Prints message when adding a task.
     * @param str String representing Task added.
     * @param size integer representing the current size of the TaskList.
     * @return
     */
    public static String addLine(String str, int size) {
        String message = "Got it. I've added this task:\n " + str
                + "\nNow you have " + size + " tasks in the list.";
        return message;
    }

    /**
     * Prints exit message.
     * @return
     */
    public static String exitLine() {
        String message = "Bye. Hope to see you again soon!";
        return message;
    }

    /**
     * Prints the list of all Tasks in the TaskList.
     * @param tl Current TaskList.
     * @return String containing list message.
     */
    public static String list(TaskList tl) {
        String message = "";
        if (tl.list.size() != 0) {
            message += "Here are the tasks in your list:\n";
            for (int i = 1; i <= tl.list.size(); i++) {
                message += " " + i + ". " + tl.list.get(i - 1).toString() + "\n";
            }
        } else {
            message = "You have no tasks in your list.";
        }
        return message;
    }

    /**
     * Produces the matching list message.
     * @param tl taskList
     * @return String containing matching list message.
     */
    public static String listMatching(ArrayList<Task> tl) {
        String result = "";
        if (tl.size() != 0) {
            result += "Here are the matching tasks in your list:\n";
            for (int i = 1; i <= tl.size(); i++) {
                result += " " + i + ". " + tl.get(i - 1).toString() + "\n";
            }
        } else {
            result = "You have no matching tasks in your list.";
        }
        return result;
    }

    public static String editMessage(String original, String newString) {
        String result = "You have successfully edited the task:\n" + original
                + "\nto:\n" + newString;
        return result;
    }

    public static String correctAnswer() {
        String result = "Correct! The answer is " + answer + "! Great job!";
        return result;
    }

    public static String wrongAnswer() {
        String result = "Sorry, the answer is " + answer + ". Better luck next time!";
        return result;
    }

    public static String getAnswer() {
        return answer;
    }
}