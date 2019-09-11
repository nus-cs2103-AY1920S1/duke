package UI;

import Data.Formatter;
import Task.Task;

import java.util.List;

public class MessageGenerator {

    Formatter formatter = new Formatter();

    /**
     * Generates messages for different user input.
     */
    public MessageGenerator() {}

    private String line() {
        return "_______________________________________________________\n";
    }

    /**
     * @return String for header of task list.
     */
    private String listTasks() {
        return "Here are the tasks in your list: ";
    }

    /**
     * @return String for header of removing task action.
     */
    private String removeTask() {
        return "Noted. I've removed this task: ";
    }

    /**
     * @return String for header of adding task action.
     */
    private String addTask() {
        return "Got it. I've added this task: ";
    }

    /**
     * @return String for header of marking task as done.
     */
    private String markDone() {
        return "Nice! I've marked this task as done:";
    }

    /**
     * @return String to indicate number of tasks in list.
     */
    private String numTask(int n) {
        return "Now you have " + n + " task(s) in the list.";
    }

    /**
     * @return String for header of finding matching tasks.
     */
    private String matchingTasks() {
        return "Here are the matching tasks in your list:";
    }

    private String updateTask() {
        return "Got it. Your task is now updated as: ";
    }

    /**
     * @return String to greet user.
     */
    private String greeting() {
        String s = formatter.format("Hello I'm Pan Pan!\n");
        s += formatter.format("What can I do for you?\n");
        return s;
    }

    /**
     * @return String when program is exited.
     */
    private String byeString() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Formats task with proper indentations.
     *
     * @return String for formatted task.
     */
    private String formatTask(Task task) {
        if (task == null) {
            return "";
        } else {
            return "  " + task.toString();
        }
    }

    /**
     * Returns a message with lined formatting.
     *
     * @param message that should be formatted.
     * @return formatted string for printing/output.
     */
    public String getLinedMessage(String message) {
        String s = "";
        s += line();
        s += formatter.format(message) + "\n";
        s += line();
        return s;
    }

    /**
     * Returns the program response when a task is removed.
     *
     * @param task Task that is to be removed.
     * @param n Number tagged to task.
     */
    public String getRemoveMessage(Task task, int n) {
        String s = line();
        s += formatter.appendStrings(removeTask(), formatTask(task), numTask(n));
        s += line();
        return s;
    }

    /**
     *
     */
    public String getUpdateMessage(Task task, int n) {
        String s = line();
        s += formatter.appendStrings(updateTask(), formatTask(task), numTask(n));
        s += line();
        return s;
    }

    /**
     * @param task Task that is to be added.
     * @param n Number tagged to task.
     * @return String that contains the task added.
     */
    public String getAddMessage(Task task, int n) {
        String s = "";
        s += line();
        s += formatter.appendStrings(addTask(), formatTask(task), numTask(n));
        s += line();
        return s;
    }

    /**
     * Returns the program response when a task is set as done.
     *
     * @param task Task that is set as done.
     * @return String to indicate task is marked as done.
     */
    public String getDoneMessage(Task task) {
        String s = line();
        s += formatter.appendStrings(markDone(), formatTask(task));
        s += line();
        return s;
    }

    /**
     * Prints the program response when user asks for list of tasks.
     *
     * @param list List of tasks in their string form.
     * @return String in lined
     */
    public String getList(List<String> list) {
        String s = line();
        s += formatter.appendStrings(formatter.format(listTasks()), list);
        s += line();
        return s;
    }

    /**
     * Prints matching tasks in a task list.
     *
     * @param list list of tasks in their string form.
     */
    public String getMatchingList(List<String> list) {
        String s = "";
        s += line();
        s += formatter.appendStrings(formatter.format(matchingTasks()), list);
        s += line();
        return s;
    }

    /**
     * Prints welcome message when program begins.
     */
    public String greet() {
        String s = line();
        s += formatter.format(greeting());
        s += line();
        return s;
    }

    /**
     * Prints goodbye message when program terminates.
     */
    public void bye() {
        formatter.printFormat(byeString());
    }
}
