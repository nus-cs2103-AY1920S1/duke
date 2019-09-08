package seedu.duke;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

        tasksMsg += IntStream.range(0, list.getSize())
                .filter((index) -> list.getTask(index) != null)
                .mapToObj((index) -> String.format("%d.%s\n", index + 1, list.getTask(index)))
                .collect(Collectors.joining());

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
        String joinedString = IntStream.range(0, list.size())
                .filter((index) -> list.get(index) != null)
                .mapToObj((index) -> String.format("%d.%s\n", index + 1, list.get(index)))
                .collect(Collectors.joining());

        return joinedString;
    }

    /**
     * Returns the empty description error message.
     *
     * @param s the type of task
     * @return the empty description error message
     */
    public static String getEmptyDescriptionMsg(String s) {
        return ("☹ OOPS!!! The description of a " + s + " command cannot be empty.\n");
    }

    /**
     * Returns the invalid input error message.
     *
     * @return the invalid input error message
     */
    public static String getInvalidInputMsg() {
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


    /**
     * Returns the invalid find input error message.
     *
     * @return the invalid find input error message
     */
    public static String getInvalidFindMsg() {
        return ("☹ OOPS!!! The keyword to find must only be one word. :-(\n");
    }

    /**
     * Returns the invalid index error message.
     *
     * @return the invalid index error message
     */
    public static String getInvalidIndexMsg(String str) {
        return ("☹ OOPS!!! The index of " + str + " command must be an integer "
                + "and be within the size of the list :-(\n");
    }

}
