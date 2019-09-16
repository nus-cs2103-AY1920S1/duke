package utilities;

import java.util.Scanner;

/**
 * Shows the interface.
 */
public class Ui {

    private final String lineBorder = "____________________________________________________________";

    /**
     * prints welcome message.
     */
    public void showWelcome() {
        final String Duke_Logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + Duke_Logo);
        System.out.println(lineBorder + "\n" + "Hello! I'm Duke" + "\n"
                + "What can I do for you?" + "\n" + lineBorder);
    }

    /**
     * returns welcome message as string.
     *
     * @return welcome message string
     */
    public String showWelcomeFX() {
        final String Duke_Logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        return "Hello from\n" + Duke_Logo + lineBorder + "\n" + "Hello! I'm Duke" + "\n"
                + "What can I do for you?" + "\n" + lineBorder;
    }

    /**
     * prints conclusion message.
     */
    public void showConclusion() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * to print conclusion message.
     *
     * @return message as String
     */
    public String showConclusionFX() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * to read command input.
     *
     * @return user input string
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * to print the long border line.
     */
    public void showLine() {
        System.out.println(lineBorder);
    }

    /**
     * to print error message.
     *
     * @param message is default parameter
     */
    public void showError(String message) {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-()");
    }

    /**
     * to return error as String.
     *
     * @param message is default parameter
     *
     * @return the error message
     */
    public String showErrorFX(String message) {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-()";
    }

    /**
     * prints error message if file is not available.
     */
    public void showLoadingError() {
        System.out.println("File not available");
    }

    /**
     * return loading error message as String.
     *
     * @return error message
     */
    public String showLoadingErrorFX() {
        return "File not available";
    }

    /**
     * print message for done command.
     *
     * @param n is the digit of task to be deleted
     *
     * @param tasks is the TaskList
     */
    public void doneMessage(int n, TaskList tasks) {
        System.out.println("Nice! I've marked this task as done: \n" + tasks.taskPrint(n));
    }

    /**
     * return done message as String.
     * @param n is the digit of task to be done
     *
     * @param tasks is the TaskList
     *
     * @return the output String
     */
    public String doneMessageFX(int n, TaskList tasks) {
        return "Nice! I've marked this task as done: \n" + tasks.taskPrint(n);
    }

    /**
     * to print delete message.
     *
     * @param n is the digit of task to be deleted
     *
     * @param tasks is the TaskList
     */
    public void deleteMessage(int n, TaskList tasks) {
        System.out.println("Noted. I've removed this task:" + "\n" + tasks.taskPrint(n)
                + "\n" + "Now you have " + (tasks.size() - 1) + " tasks in the list.");
    }

    /**
     * to return the delete message as String.
     *
     * @param n is the digit of task to be deleted
     *
     * @param tasks is the TaskList
     *
     * @return the delete message
     */
    public String deleteMessageFX(int n, TaskList tasks) {
        return "Noted. I've removed this task:" + "\n" + tasks.taskPrint(n)
                + "\n" + "Now you have " + (tasks.size() - 1) + " tasks in the list.";
    }

    /**
     * prints all contents in list format.
     *
     * @param tasks is the TaskList
     */
    public void listCommand(TaskList tasks) {
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + ". " + tasks.get(i - 1).printer());
        }
    }

    /**
     * return list as String.
     *
     * @param tasks is the TaskList
     *
     * @return the list
     */
    public String listCommandFX(TaskList tasks) {
        StringBuilder s = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            if (i == tasks.size()) {
                s.append(i).append(". ").append(tasks.get(i - 1).printer());
            } else {
                s.append(i).append(". ").append(tasks.get(i - 1).printer()).append("\n");
            }
        }
        return s.toString();
    }

    /**
     * to print the find command results.
     *
     * @param tasks is the TaskList
     */
    public void findCommand(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Sorry, we couldn't find any results!");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            this.listCommand(tasks);
        }
    }

    /**
     * return find results as String.
     *
     * @param tasks is the TaskList
     *
     * @return find results
     */
    public String findCommandFX(TaskList tasks) {
        if (tasks.isEmpty()) {
            return "Sorry, we couldn't find any results!";
        } else {
            return "Here are the matching tasks in your list:" + "\n" + this.listCommandFX(tasks);
        }
    }



}
