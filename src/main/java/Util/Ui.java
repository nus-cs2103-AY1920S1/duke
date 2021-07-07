package util;

public class Ui {

    /**
     * Duke welcome message.
     *
     * @return logo and message
     */
    public static String welcomeMsg() {
        String logoEra = " ____  ____       /\\ \n"
                + "| ___||  _ \\     /  \\\n"
                + "||___ | |_| |   / /\\ \\\n"
                + "| ___||  __/   / /__\\ \\\n"
                + "||___ | |\\ \\  / ______ \\\n"
                + "|____||_| \\_\\/_/      \\_\\\n";
        return ("Hello from\n" + logoEra + "What can I do for you?");
    }

    public static String byeMsg() {
        return ("Bye. Hope to see you again soon!\n");
    }

    public static String listMsg() {
        return ("Here are the tasks in your list:\n");
    }

    public static String doneMsg() {
        return ("Nice! I've marked this task as done:\n");
    }

    public static String findMsg() {
        return ("Here are the matching tasks in your list: \n");
    }

    public static String duplicateMsg() {
        return ("I've found following duplicate tasks in your taskList:\n");
    }

    public static String duplicateEmptyMsg() {
        return ("Congrat! No duplicates found!\n");
    }

    public static String unknownMsg() {
        return ("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
    }

    public static String emptyTaskMsg(String s) {
        return ("☹ OOPS!!! The description of a " + s + " cannot be empty.\n");
    }

    public static String invalidNumMsg() {
        return ("☹ OOPS!!! You have entered an invalid number.\n");
    }

    public static String outOfBoundMsg() {
        return ("☹ OOPS!!! Out of range, the task does not exist.\n");
    }

    public static String missingDeadlineMsg() {
        return ("☹ OOPS!!! You must specify a deadline date.\n");
    }

    public static String missingEventMsg() {
        return ("☹ OOPS!!! You must specify a event date.\n");
    }

    public static String deleteEmptyMsg() {
        return ("☹ OOPS!!! You cannot delete an empty entry.\n");
    }

    public static String doneErrorMsg() {
        return ("☹ OOPS!!! This entry does not exist.\n");
    }

    public static String findErrorMsg() {
        return ("☹ OOPS!!! You must specify the description.\n");
    }

    public static String findEmptyMsg() {
        return ("☹ OOPS!!! No matching task is found.\n");
    }

    public static String showErrorMsg(Exception error) {
        return ("☹ OOPS!!!" + error.toString() + "\n");
    }

}