package seedu.duke;

import java.util.ArrayList;

/**
 * Ui is a class that deals with interactions with the user. It contains operations that
 * read commands by the users and prints messages.
 */
public class Ui {

    /**
     * Constructor of the Ui class.
     */
    public Ui() {
    }

    /**
     * Returns the greeting message in the form of a string.
     */
    public static String greet() {
        String message = "Hello! I'm Duke\n"
                + "What can I do for you?";
        return message;
    }

    /**
     * Returns the exit message in the form of a string.
     */
    public static String exit() {
        String byeMessage = "Bye. Hope to see you again soon!";
        return byeMessage;
    }

    /**
     * Returns the error message in the form of a string if the storage cannot load.
     */
    public static String showLoadingError() {
        String msg = "Storage cannot be loaded!\n";
        return msg;
    }

    /**
     * Returns message and all the tasks in the list in the form of a string.
     *
     * @param list the list of tasks
     */
    public static String printList(TaskList list) {
        assert list != null : "TaskList cannot be null";
        String tasksMsg = "Here are the tasks in your list:\n";
        for (int j = 0; (j < list.getSize()) && list.getTask(j) != null; j++) {
            tasksMsg += j + 1 + "." + list.getTask(j) + "\n";
        }
        return tasksMsg;
    }

    /**
     * Returns the add message in the form of a string.
     */
    public static String printAddMsg() {
        return ("Got it. I've added this task:\n");
    }

    /**
     * Returns the message stating the number of tasks in the list.
     *
     * @param list the list of tasks
     */
    public static String printNumTask(TaskList list) {
        return ("Now you have " + list.getSize() + " tasks in the list.\n");
    }

    /**
     * Returns the remove message in the form of a string.
     */
    public static String printRemoveMsg() {
        return ("Noted. I've removed this task:\n");
    }

    /**
     * Returns the latest task in the list.
     *
     * @param list the list of tasks
     */
    public static String printLatest(TaskList list) {
        //assert false : "list is now not null";
        // assert list != null : "TaskList cannot be null";
        return list.getTask(list.getSize() - 1) + "\n";
    }

    /**
     * Returns the matching message.
     */
    public static String printMatchingMsg() {
        return ("Here are the matching tasks in your list:\n");
    }

    /**
     * Returns the tasks in the matching list.
     *
     * @param list the list of matching tasks
     */
    public static String printMatchingList(ArrayList<Task> list) {
        String string = "";
        for (int j = 0; (j < list.size()) && list.get(j) != null; j++) {
            string += j + 1 + "." + list.get(j) + "\n";
        }
        return string;
    }

    /**
     * Returns the empty description error message.
     *
     * @param s the type of task
     * @return the empty description error message
     */
    public static String emptyDescriptionMsg(String s) {
        return ("☹ OOPS!!! The description of a " + s + " cannot be empty.\n");
    }

    /**
     * Returns the invalid input error message.
     *
     * @return the invalid input error message
     */
    public static String invalidInputMsg() {
        return ("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
    }

    /**
     * Returns the error message.
     *
     * @param s the error message
     */
    public String printErrMsg(String s) {
        return s + "\n";
    }

}
