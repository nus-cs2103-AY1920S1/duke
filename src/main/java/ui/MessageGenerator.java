package ui;

import storage.Formatter;
import task.Task;

import java.util.ArrayList;
import java.util.List;

public class MessageGenerator {

    Formatter formatter = new Formatter();

    private static String LINE = "_______________________________________________________\n";

    /**
     * Generates messages for different user input.
     */
    public MessageGenerator() {

    }

    private String line() {
        return LINE;
    }

    private String listTasks() {
        return "Here are the tasks in your list: ";
    }

    private String removeTask() {
        return "Noted. I've removed this task: ";
    }

    private String addTask() {
        return "Got it. I've added this task: ";
    }

    private String markDone() {
        return "Nice! I've marked this task as done:";
    }

    private String numTask(int n) {
        return "Now you have " + n + " task(s) in the list.";
    }

    private String matchingTasks() {
        return "Here are the matching tasks in your list:";
    }

    private String updateTask() {
        return "Got it. Your task is now updated as: ";
    }

    private String greeting() {
        String s = formatter.format("Hello I'm Pan Pan!\n");
        s += formatter.format("What can I do for you?\n");
        s += formatter.format("For list of commands, type help.\n");
        return s;
    }

    private String byeString() {
        return "Bye. Hope to see you again soon!";
    }

    private List<String> helpMessage() {
        List<String> messageChain = new ArrayList<>(20);
        messageChain.add("Here's the list of commands:");
        messageChain.add("To create a new Todo, type:");
        messageChain.add(formatter.format("todo <Task Description>"));
        messageChain.add("To create a new Deadline, type:");
        messageChain.add(formatter.format("deadline <Task Description> /by<DD/MM/YYYY> <HHMM>"));
        messageChain.add("To create a new Event, type:");
        messageChain.add(formatter.format("event <Task Description> /at<DD/MM/YYYY> <HHMM>"));
        messageChain.add("To list all tasks:");
        messageChain.add(formatter.format("list"));
        messageChain.add("To find a task containing keyword(s)");
        messageChain.add(formatter.format("find <keyword(s)>"));
        messageChain.add("To delete a task:");
        messageChain.add(formatter.format("delete <TaskNo>"));
        messageChain.add("To mark a task as done, type:");
        messageChain.add(formatter.format("done <TaskNo>"));
        messageChain.add("To update a task description, type:");
        messageChain.add(formatter.format("update | <TaskNo> | desc | <New Description>"));
        messageChain.add("To update a task time, type:");
        messageChain.add(formatter.format("update | <TaskNo> | time | <New Time>"));
        messageChain.add("To update a task date, type:");
        messageChain.add(formatter.format("update | <TaskNo> | date | <New Date>"));
        return messageChain;
    }

    /**
     * Formats task with indentations.
     *
     * @param task that is to be formatted.
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
     * Returns program response when a task is updated.
     *
     * @param task Task to be updated.
     * @param n Number of tasks in the list.
     * @return String containing update message.
     */
    public String getUpdateMessage(Task task, int n) {
        String s = line();
        s += formatter.appendStrings(updateTask(), formatTask(task), numTask(n));
        s += line();
        return s;
    }

    /**
     * Returns program response when a task is added.
     *
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
     * Returns the program response when user asks for list of tasks.
     *
     * @param list List of tasks in their string form.
     * @return String of list in lined formatting.
     */
    public String getList(List<String> list) {
        String s = line();
        s += formatter.appendStrings(formatter.format(listTasks()), list);
        s += line();
        return s;
    }

    /**
     * Returns matching tasks in a task list.
     *
     * @param list list of tasks in their string form.
     * @return Appended string containing tasks.
     */
    public String getMatchingList(List<String> list) {
        String s = "";
        s += line();
        s += formatter.appendStrings(formatter.format(matchingTasks()), list);
        s += line();
        return s;
    }

    /**
     * Returns welcome message when program begins.
     *
     * @return String containing Greeting message.
     */
    public String greet() {
        String s = line();
        s += greeting();
        s += line();
        return s;
    }

    /**
     * Returns goodbye message when program terminates.
     *
     * @return String containing goodbye message.
     */
    public String bye() {
        String s = line();
        s += formatter.format(byeString());
        s += line();
        return s;
    }

    /**
     * Returns help message when user asks for help.
     *
     * @return String containing list of commands.
     */
    public String getHelpMessage() {
        String s = line();
        s += formatter.appendStrings(helpMessage().toArray(new String[(helpMessage().size())]));
        s += line();
        return s;
    }
}
