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
     * @return
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
    public static ChatDisplay showError(String str) {
        ChatDisplay result = new ChatDisplay(str, ChatDisplay.Expression.CONFUSED);
        return result;
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
    public static ChatDisplay doneLine(String str) {
        String message = "Nice! I've marked this task as done:\n " + str;
        ChatDisplay result = new ChatDisplay(message, ChatDisplay.Expression.SINGING);
        return result;
    }

    /**
     * Prints message when deleting a task.
     * @param str String representing Task deleted.
     * @param size integer representing the current size of the TaskList.
     * @return
     */
    public static ChatDisplay deleteLine(String str, int size) {
        String delete = "You've finished! Good job! I'll remove this task for you: \n   "
                + str + "\nNow you have " + size + " tasks in the list.";
        ChatDisplay result = new ChatDisplay(delete, ChatDisplay.Expression.SPARKLY);
        return result;
    }

    /**
     * Prints message when adding a task.
     * @param str String representing Task added.
     * @param size integer representing the current size of the TaskList.
     * @return
     */
    public static ChatDisplay addLine(String str, int size) {
        String message = "Alright! I'll add this task for you:\n " + str
                + "\nNow you have " + size + " tasks in the list.";
        ChatDisplay result = new ChatDisplay(message, ChatDisplay.Expression.SINGING);
        return result;
    }

    /**
     * Prints exit message.
     * @return
     */
    public static ChatDisplay exitLine() {
        String message = "Bye. Hope to see you again soon!";
        ChatDisplay result = new ChatDisplay(message, ChatDisplay.Expression.SINGING);
        return result;
    }

    /**
     * Prints the list of all Tasks in the TaskList.
     * @param tl Current TaskList.
     * @return String containing list message.
     */
    public static ChatDisplay list(TaskList tl) {
        String message = "";
        if (tl.list.size() != 0) {
            message += "Here are the tasks in your list:\n";
            for (int i = 1; i <= tl.list.size(); i++) {
                message += " " + i + ". " + tl.list.get(i - 1).toString() + "\n";
            }
        } else {
            message = "You have no tasks in your list.";
        }
        ChatDisplay result = new ChatDisplay(message, ChatDisplay.Expression.NEUTRAL);
        return result;
    }

    /**
     * Produces the matching list message.
     * @param tl taskList
     * @return String containing matching list message.
     */
    public static ChatDisplay listMatching(ArrayList<Task> tl) {
        String message = "";
        if (tl.size() != 0) {
            message += "Here are the matching tasks in your list:\n";
            for (int i = 1; i <= tl.size(); i++) {
                message += " " + i + ". " + tl.get(i - 1).toString() + "\n";
            }
        } else {
            message = "You have no matching tasks in your list.";
        }
        ChatDisplay result = new ChatDisplay(message, ChatDisplay.Expression.NEUTRAL);
        return result;
    }

    public static ChatDisplay editMessage(String original, String newString) {
        String message = "You have successfully edited the task:\n" + original
                + "\nto:\n" + newString;
        ChatDisplay result = new ChatDisplay(message, ChatDisplay.Expression.NEUTRAL);
        return result;
    }

    public static ChatDisplay correctAnswer() {
        String message = "Correct! The answer is " + answer + "! Great job!";
        ChatDisplay result = new ChatDisplay(message, ChatDisplay.Expression.SPARKLY);
        return result;
    }

    public static ChatDisplay wrongAnswer() {
        String message = "Sorry, the answer is " + answer + ". Better luck next time!";
        ChatDisplay result = new ChatDisplay(message, ChatDisplay.Expression.CONCERNED);
        return result;
    }

    public static String getAnswer() {
        return answer;
    }

    public ChatDisplay questionMessage(String question) {
        ChatDisplay result = new ChatDisplay(question, ChatDisplay.Expression.NEUTRAL);
        return result;
    }
}